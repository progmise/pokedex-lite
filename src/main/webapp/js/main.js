$('.carousel').carousel({
    interval: 4000
})

/* 
Recupera los Tipos y se generan los checkboxes
con sus respectivos valores.
*/
$.get('/pokedex-lite/servletType', function (responseJson) {
    $.each(responseJson, function (index, type) {
        var $div = $('<div class="form-check form-check-inline">').appendTo($('#listTypes'));

        $(`<input class="form-check-input" type="checkbox" id="inlineCheckBox${index + 1}" value="${type.name}" name="types">`).appendTo($div);
        $(`<label class="form-check-label" for="inlineCheckBox${index + 1}">`).text(type.name).appendTo($div);
    });
});

$(document).ready(function () {
    var idPk;

    //Recupera el form y crea un objeto Pokemon para persistir en el backend
    $('#submitCrear').click(function (e) {
        var formCrear = document.getElementById('formCrear');
        var formDataCrear = new FormData(formCrear);

        var frontPokemon = parseFormToPokemon(formDataCrear);

        var json = JSON.stringify(frontPokemon);

        e.preventDefault();
        $.ajax({
            type: 'POST',
            url: '/pokedex-lite/servletPokemon',
            data: json,
            processData: false,
            success: function () {

            }
        });
    });

    //Recupera el form y crea un objeto Pokemon para recuperar sus datos del backend
    $('#submitBuscar').click(function (e) {
        var formBuscar = document.getElementById('formBuscar');
        var formDataBuscar = new FormData(formBuscar);

        var frontPokemon = parseFormToPokemon(formDataBuscar);

        var json = encodeURI(JSON.stringify(frontPokemon));

        e.preventDefault();
        $.ajax({
            type: 'GET',
            url: '/pokedex-lite/servletPokemon',
            data: json,
            processData: false,
            success: function (pokemonJson) {
                var backPokemon = pokemonJson;
                idPk = '' + backPokemon.id;

                
                //Recuperados los datos, se empieza a formatear el form con dichos datos
                $('#formEditar').find('#inputNombre').attr('value', backPokemon.name);
                
                $.get('/pokedex-lite/servletType', function (responseJson) {
                    $.each(responseJson, function (index, type) {
                        /* 
                        Recupera los Tipos y se generan los checkboxes
                        con sus respectivos valores.
                        Además, quedan checked los Tipos que ya tiene el Pokemon.
                        */                        
                        var $div = $('<div class="form-check form-check-inline">').appendTo($('#listTypesEdit'));

                        $(`<input class="form-check-input" type="checkbox" id="inlineCheckBox${index + 1}" value="${type.name}" name="types">`).appendTo($div);
                        $(`<label class="form-check-label" for="inlineCheckBox${index + 1}">`).text(type.name).appendTo($div);                   

                        if ((typeof backPokemon.types).localeCompare('string') == 0) {
                            if (($div[0].firstChild.value).localeCompare(backPokemon.types) == 0) {
                                $div[0].firstChild.checked = true;
                            }    
                        }
                        else {
                            $.each(backPokemon.types, function (index) {
                                if (($div[0].firstChild.value).localeCompare(backPokemon.types[index]) == 0) {
                                    $div[0].firstChild.checked = true;
                                }
                            })                            
                        }
                        
                    });

                    $.each(responseJson, function (index, type) {
                        /* 
                        Recupera los Tipos y se generan los checkboxes
                        con sus respectivos valores.
                        */
                        var $div = $('<div class="form-check form-check-inline">').appendTo($('#listTypesEvolution'));
                
                        $(`<input class="form-check-input" type="checkbox" id="inlineCheckBox${index + 1}" value="${type.name}" name="types">`).appendTo($div);
                        $(`<label class="form-check-label" for="inlineCheckBox${index + 1}">`).text(type.name).appendTo($div);
                    });                    
                });

                $('#formEditar').find('#inputNivel').attr('value', backPokemon.levelIsFound);
                
            }
        });
    });

    $('#submitActualizar').click(function (e) {
        var formPokemon = document.getElementById('formEditar');
        var formDataPokemon = new FormData(formPokemon);

        var formEvolucion = document.getElementById('formEvolucion');
        var formDataEvolucion = new FormData(formEvolucion);

        var pokemon = parseFormToPokemon(formDataPokemon);
        pokemon.id = idPk;

        var evolucion = parseFormToEvolution(formDataEvolucion);       

        var jsonPokemon = JSON.stringify(pokemon);
        var jsonEvolucion =  JSON.stringify(evolucion);

        var json = "{ json_pokemon:"+jsonPokemon+", "+"json_evolucion:"+jsonEvolucion+" }"

        e.preventDefault();
        $.ajax({
            type: 'PUT',
            url: '/pokedex-lite/servletPokemon',
            data: json,
            processData: false,
            success: function () {

            }
        });
    });
    
    $('#submitBuscador').click(function (e) {
        var formBuscador = document.getElementById('formBuscador');
        var formDataBuscador = new FormData(formBuscador);

        var pokemon = parseFormToPokemon(formDataBuscador);

        var json = encodeURI(JSON.stringify(pokemon));

        e.preventDefault();
        $.ajax({
            type: 'GET',
            url: '/pokedex-lite/servletSearcher',
            data: json,
            processData: false,
            success: function (pokemonJson) {
                var pokemon = pokemonJson;

                switch (pokemon.searchOption) {
                    case 'nombreTiposNivel':
                        var types = (typeof pokemon.types === 'string') ? pokemon.types : pokemon.types.join(', ');                      

                        var $div = containerGenerator('#listMedias', pokemon);
                        $(`<div class="p-2 bd-highlight"><h4>Pokemon - ${pokemon.name}</h4></div>`).appendTo($div);
                        $(`<div class="p-2 bd-highlight"><p><b>Nombre: </b>${pokemon.name}</p></div>`).appendTo($div);
                        $(`<div class="p-2 bd-highlight"><p><b>Tipo/s: </b>${types}</p></div>`).appendTo($div);
                        $(`<div class="p-2 bd-highlight"><p><b>Nivel: </b>${pokemon.levelIsFound}</p></div>`).appendTo($div);
                        break;
                    case 'habilidadesEvoluciones':
                        var abilities = (typeof pokemon.abilities === 'string') ? pokemon.abilities : pokemon.abilities.join(', ');
                        var evolutions = (typeof pokemon.evolutions === 'string') ? evolutions = pokemon.evolutions : pokemon.evolutions.join(', ');                   

                        var $div = containerGenerator('#listMedias', pokemon);
                        $(`<div class="p-2 bd-highlight"><h4>Pokemon - ${pokemon.name}</h4></div>`).appendTo($div);
                        $(`<div class="p-2 bd-highlight"><p><b>Habilidades: </b>${abilities}</p></div>`).appendTo($div);
                        $(`<div class="p-2 bd-highlight"><p><b>Evoluciones: </b>${evolutions}</p></div>`).appendTo($div);                    
                        break;
                    case 'evolucionesInformacion':
                        $.each(pokemon.evolutions, function (index, evolution) {
                            var types = (typeof evolution.types === 'string') ? evolution.types : evolution.types.join(', '); 

                            var $div = containerGenerator('#listMedias', evolution)
                            $(`<div class="p-2 bd-highlight"><h4>${pokemon.name} - Evolucion ${'0'+(index+1)}</h4></div>`).appendTo($div);
                            $(`<div class="p-2 bd-highlight"><p><b>Nombre: </b>${evolution.name}</p>`).appendTo($div);
                            $(`<div class="p-2 bd-highlight"><p><b>Tipo/s: </b>${types}</p>`).appendTo($div);
                            $(`<div class="p-2 bd-highlight"><p><b>Nivel requerido: </b>${evolution.levelNeeded}</p>`).appendTo($div);
                        });
                        break;                
                    default:
                        break;
                }
            }
        });
    });
    
    $('.mostrar').click(function (e) {
        var searchParams = new URLSearchParams(); 
        searchParams.append('pagina', '0');
        searchParams.append('cantidad', '10')

        var queryString = searchParams.toString();

        $.ajax({
            type: 'GET',
            url: '/pokedex-lite/servletList',
            data: queryString,
            processData: false,
            success: function (listPks) {
                var pokemons = listPks;
                $.each(pokemons, function (index, pokemon) {
                    var types;

                    if (typeof pokemon.types === 'string') {
                        types = pokemon.types;
                    }
                    else {
                        types = pokemon.types.join(', ');
                    }

                    var $div = containerGenerator('#listPokemons', pokemon);
                    $(`<div class="p-2 bd-highlight"><h4>Pokemon - ${pokemon.id}</h4></div>`).appendTo($div);
                    $(`<div class="p-2 bd-highlight"><p><b>Nombre: </b>${pokemon.name}</p></div>`).appendTo($div);
                    $(`<div class="p-2 bd-highlight"><p><b>Tipo/s: </b>${types}</p></div>`).appendTo($div);
                    $(`<div class="p-2 bd-highlight"><p><b>Nivel: </b>${pokemon.levelIsFound}</p></div>`).appendTo($div);
                });
            }
        });
    });    
});

/* 
Función para armar un objeto Pokemon, a partir de un Form, 
para posteriormente ser enviado al backend
*/
function parseFormToPokemon(form) {
    var pokemon = {};

    form.forEach((value, key) => {
        if (!Reflect.has(pokemon, key)) {
            pokemon[key] = value;
            return;
        }
        if (!Array.isArray(pokemon[key])) {
            pokemon[key] = [pokemon[key]];
        }
        pokemon[key].push(value);
    });

    if (!('types' in pokemon)) {
        pokemon['types'] = '';
    }

    if (!('abilities' in pokemon)) {
        pokemon['abilities'] = '';
    }

    if (!('evolutions' in pokemon)) {
        pokemon['evolutions'] = '';
    }

    if (!('id' in pokemon)) {
        pokemon['id'] = '0';
    }    

    return pokemon;
}

/* 
Función para armar un objeto Evolucion, a partir de un Form, 
para posteriormente ser enviado al backend.
*/
function parseFormToEvolution(form) {
    var evolucion = {};

    form.forEach((value, key) => {
        if (!Reflect.has(evolucion, key)) {
            evolucion[key] = value;
            return;
        }
        if (!Array.isArray(evolucion[key])) {
            evolucion[key] = [evolucion[key]];
        }
        evolucion[key].push(value);
    });

    if (!('types' in evolucion)) {
        evolucion['types'] = '';
    }  

    return evolucion;
}

/*
Función para generar dinamicamente los contenedores
de los medias, donde se visualizan los datos del Pokemon.
*/
function containerGenerator(id, object) {
    var $divLvl1 = $('<div class="media alert alert-info mt-4 w-75">').appendTo($(id));
    $(`<img src="https://via.placeholder.com/200" width="200" style="border-radius: 15px;" alt="foto de ${object.name}">`).appendTo($divLvl1);
    var $divLvl2 = $('<div class="media-body ml-4">').appendTo($divLvl1);
    var $divLvl3 = $('<div class="d-flex justify-content-between align-items-start flex-column bd-highlight" style="height: 200px;">').appendTo($divLvl2);
    
    return $divLvl3;
}

/*
De haber sido seleccionado el checkbox de Evolucion, se desplegará 
su formulario, de ser deseleccionado, se ocultará.
*/
$(function() {
    ($('#formEditar').find('#checkEvolucion')).on('change', function() {
        if (this.checked) {
            $('#containerFormEvolucion').toggle(true);
        }
        else { 
            $('#containerFormEvolucion').toggle(false);
        }
    });
});

/* Model for checkbox
<div class="form-check form-check-inline">
    <input class="form-check-input" type="checkbox" id="inlineCheckbox1" value="tipo01">
    <label class="form-check-label" for="inlineCheckbox1">Tipo 01</label>
</div>
*/

/* Model for media
<!-- Beggining of media -->
<div class="media alert alert-info mt-4 w-75">
    <img src="https://via.placeholder.com/200" width="200" style="border-radius: 15px;" alt="foto de pokemon.nombre">
    <div class="media-body ml-4">
        <div class="d-flex justify-content-between align-items-start flex-column bd-highlight" style="height: 200px;">
            <div class="p-2 bd-highlight"><h4>Pokemon</h4></div>
            <div class="p-2 bd-highlight"><p><b>Nombre: </b>Pokemon.nombre</p></div>
            <div class="p-2 bd-highlight"><p><b>Tipo/s: </b>Pokemon.tipos</p></div>
            <div class="p-2 bd-highlight"><p><b>Nivel: </b>Pokemon.nivel</p></div>
        </div>        
    </div>
</div>
*/