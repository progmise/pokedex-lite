package com.certant.app.ddl;

import com.certant.app.manager.SessionManager;
import com.certant.app.model.Ability;
import com.certant.app.model.Evolution;
import com.certant.app.model.Pokemon;
import com.certant.app.model.Type;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class DatabaseGenerator {
    public static void main(String[] args) {

        Session s = SessionManager.getSession();
        Transaction t = s.beginTransaction();        
        
        //Types
        Type t1 = new Type();
        t1.setName("Normal");
        
        Type t2 = new Type();
        t2.setName("Fire");
        
        Type t3 = new Type();
        t3.setName("Water");
        
        Type t4 = new Type();
        t4.setName("Electric");
        
        Type t5 = new Type();
        t5.setName("Grass");
        
        Type t6 = new Type();
        t6.setName("Ice");
        
        Type t7 = new Type();
        t7.setName("Fighting");
        
        Type t8 = new Type();
        t8.setName("Poison");
        
        Type t9 = new Type();
        t9.setName("Ground");
        
        Type t10 = new Type();
        t10.setName("Flying");
        
        Type t11 = new Type();
        t11.setName("Psychic");
        
        Type t12 = new Type();
        t12.setName("Bug");
        
        Type t13 = new Type();
        t13.setName("Rock");
        
        Type t14 = new Type();
        t14.setName("Ghost");
        
        Type t15 = new Type();
        t15.setName("Dragon");
        
        Type t16 = new Type();
        t16.setName("Dark");
        
        Type t17 = new Type();
        t17.setName("Steel");
        
        Type t18 = new Type();
        t18.setName("Fairy");
        
        s.save(t1);
        s.save(t2);
        s.save(t3);
        s.save(t4);
        s.save(t5);
        s.save(t6);
        s.save(t7);
        s.save(t8);
        s.save(t9);
        s.save(t10);
        s.save(t11);
        s.save(t12);
        s.save(t13);
        s.save(t14);
        s.save(t15);
        s.save(t16);
        s.save(t17);
        s.save(t18);
        
        //Abilities
        Ability ay1 = new Ability();
        ay1.setName("Overgrow");
        
        Ability ay2 = new Ability();
        ay2.setName("Chlorophyll");

        Ability ay3 = new Ability();
        ay3.setName("Blaze");

        Ability ay4 = new Ability();
        ay4.setName("Solar Power");

        Ability ay5 = new Ability();
        ay5.setName("Torrent");

        Ability ay6 = new Ability();
        ay6.setName("Rain Dish");

        Ability ay7 = new Ability();
        ay7.setName("Shield Dust");

        Ability ay8 = new Ability();
        ay8.setName("Run Away");

        Ability ay9 = new Ability();
        ay9.setName("Keen Eye");

        Ability ay10 = new Ability();
        ay10.setName("Tangled Feet");

        Ability ay11 = new Ability();
        ay11.setName("Guts");

        Ability ay12 = new Ability();
        ay12.setName("Hustle");    

        Ability ay13 = new Ability();
        ay13.setName("Sniper");    

        Ability ay14 = new Ability();
        ay14.setName("Intimidate");    

        Ability ay15 = new Ability();
        ay15.setName("Shed Skin");    

        Ability ay16 = new Ability();
        ay16.setName("Unnerve");    

        Ability ay17 = new Ability();
        ay17.setName("Sand Veil");    

        Ability ay18 = new Ability();
        ay18.setName("Sand Rush");
        
        Ability ay19 = new Ability();
        ay19.setName("Effect Spore"); 
        
        Ability ay20 = new Ability();
        ay20.setName("Dry Skin");
        
        Ability ay21 = new Ability();
        ay21.setName("Damp");

        s.save(ay1);
        s.save(ay2);
        s.save(ay3);
        s.save(ay4);
        s.save(ay5);
        s.save(ay6);
        s.save(ay7);
        s.save(ay8);
        s.save(ay9);
        s.save(ay10);
        s.save(ay11);
        s.save(ay12);
        s.save(ay13);
        s.save(ay14);
        s.save(ay15);
        s.save(ay16);
        s.save(ay17);
        s.save(ay18);
        s.save(ay19);
        s.save(ay20);
        s.save(ay21);
        
        //Pokemons
        Pokemon pk1 = new Pokemon();
        pk1.setName("Bulbasaur");
        pk1.setLevelIsFound(4);
        
        Pokemon pk2 = new Pokemon();
        pk2.setName("Charmander");
        pk2.setLevelIsFound(1);
        
        Pokemon pk3 = new Pokemon();
        pk3.setName("Squirtle");
        pk3.setLevelIsFound(10);
        
        Pokemon pk4 = new Pokemon();
        pk4.setName("Caterpie");
        pk4.setLevelIsFound(2);
        
        Pokemon pk5 = new Pokemon();
        pk5.setName("Weedle");
        pk5.setLevelIsFound(6);
        
        Pokemon pk6 = new Pokemon();
        pk6.setName("Pidgey");
        pk6.setLevelIsFound(6);
        
        Pokemon pk7 = new Pokemon();
        pk7.setName("Rattata");
        pk7.setLevelIsFound(15);
        
        Pokemon pk8 = new Pokemon();
        pk8.setName("Spearow");
        pk8.setLevelIsFound(13);
        
        Pokemon pk9 = new Pokemon();
        pk9.setName("Ekans");
        pk9.setLevelIsFound(21);
        
        Pokemon pk10 = new Pokemon();
        pk10.setName("Sandshrew");
        pk10.setLevelIsFound(19);
        
        Pokemon pk11= new Pokemon();
        pk11.setName("Paras");
        pk11.setLevelIsFound(2);

        s.save(pk1);
        s.save(pk2);
        s.save(pk3);
        s.save(pk4);
        s.save(pk5);
        s.save(pk6);
        s.save(pk7);
        s.save(pk8);
        s.save(pk9);
        s.save(pk10);
        s.save(pk11);
        
        //Evolutions
        Evolution ev1 = new Evolution();
        ev1.setName("Ivysaur");
        ev1.setLevelNeeded(16);
        
        Evolution ev2 = new Evolution();
        ev2.setName("Venusaur");
        ev2.setLevelNeeded(32);

        Evolution ev3 = new Evolution();
        ev3.setName("Charmeleon");
        ev3.setLevelNeeded(16);
        
        Evolution ev4 = new Evolution();
        ev4.setName("Charizard");
        ev4.setLevelNeeded(36);         

        Evolution ev5 = new Evolution();
        ev5.setName("Wartortle");
        ev5.setLevelNeeded(16);
        
        Evolution ev6 = new Evolution();
        ev6.setName("Blastoise");
        ev6.setLevelNeeded(36);

        Evolution ev7 = new Evolution();
        ev7.setName("Metapod");
        ev7.setLevelNeeded(7);
        
        Evolution ev8 = new Evolution();
        ev8.setName("Butterfree");
        ev8.setLevelNeeded(10);         

        Evolution ev9 = new Evolution();
        ev9.setName("Kakuna");
        ev9.setLevelNeeded(7);
        
        Evolution ev10 = new Evolution();
        ev10.setName("Beedrill");
        ev10.setLevelNeeded(10);

        Evolution ev11 = new Evolution();
        ev11.setName("Pidgeotto");
        ev11.setLevelNeeded(18);
        
        Evolution ev12 = new Evolution();
        ev12.setName("Pidgeot");
        ev12.setLevelNeeded(36);         

        Evolution ev13 = new Evolution();
        ev13.setName("Raticate");
        ev13.setLevelNeeded(20);
        
        Evolution ev14 = new Evolution();
        ev14.setName("Fearow");
        ev14.setLevelNeeded(20);

        Evolution ev15 = new Evolution();
        ev15.setName("Arbok");
        ev15.setLevelNeeded(22);
        
        Evolution ev16 = new Evolution();
        ev16.setName("Sandslash");
        ev16.setLevelNeeded(22);

        Evolution ev17 = new Evolution();
        ev17.setName("Parasect");
        ev17.setLevelNeeded(24);
        
        s.save(ev1);
        s.save(ev2);
        s.save(ev3);
        s.save(ev4);
        s.save(ev5);
        s.save(ev6);
        s.save(ev7);
        s.save(ev8);
        s.save(ev9);
        s.save(ev10);
        s.save(ev11);
        s.save(ev12);
        s.save(ev13);
        s.save(ev14);
        s.save(ev15);
        s.save(ev16);
        s.save(ev17);
        
        //Relations
        //Pokemon01
        pk1.getAbilities().add(ay1);
        pk1.getAbilities().add(ay2);
        pk1.getTypes().add(t5);
        pk1.getTypes().add(t8);
        pk1.getEvolutions().add(ev1);
        pk1.getEvolutions().add(ev2);
        s.update(pk1);
        
        ay1.getPokemons().add(pk1);
        ay2.getPokemons().add(pk1);
        s.update(ay1);
        s.update(ay2);
        
        t5.getPokemons().add(pk1);
        t8.getPokemons().add(pk1);
        s.update(t5);
        s.update(t8);
        
        ev1.setPokemon(pk1);
        ev1.getTypes().add(t5);
        ev1.getTypes().add(t8);
        s.update(ev1);
        
        ev2.setPokemon(pk1);
        ev2.getTypes().add(t5);
        ev2.getTypes().add(t8);
        s.update(ev2);
        
        t5.getEvolutions().add(ev1);
        t5.getEvolutions().add(ev2);
        s.update(t5);
        
        t8.getEvolutions().add(ev1);
        t8.getEvolutions().add(ev2);
        s.update(t8);
        
        //Pokemon02
        pk2.getAbilities().add(ay3);
        pk2.getAbilities().add(ay4);
        pk2.getTypes().add(t2);
        pk2.getEvolutions().add(ev3);
        pk2.getEvolutions().add(ev4);
        s.update(pk2);
        
        ay3.getPokemons().add(pk2);
        ay4.getPokemons().add(pk2);
        s.update(ay3);
        s.update(ay4);
        
        t2.getPokemons().add(pk2);
        s.update(t2);
        
        ev3.setPokemon(pk2);
        ev3.getTypes().add(t2);
        s.update(ev3);
        
        ev4.setPokemon(pk2);
        ev4.getTypes().add(t2);
        s.update(ev4);
        
        t2.getEvolutions().add(ev3);
        t2.getEvolutions().add(ev4);
        s.update(t2);
        
        //Pokemon03
        pk3.getAbilities().add(ay5);
        pk3.getAbilities().add(ay6);
        pk3.getTypes().add(t3);
        pk3.getEvolutions().add(ev5);
        pk3.getEvolutions().add(ev6);
        s.update(pk3);
        
        ay5.getPokemons().add(pk3);
        ay6.getPokemons().add(pk3);
        s.update(ay5);
        s.update(ay6);
        
        t3.getPokemons().add(pk3);
        s.update(t3);
        
        ev5.setPokemon(pk3);
        ev5.getTypes().add(t3);
        s.update(ev5);
        
        ev6.setPokemon(pk3);
        ev6.getTypes().add(t3);
        s.update(ev6);
        
        t3.getEvolutions().add(ev5);
        t3.getEvolutions().add(ev6);
        s.update(t3);
        
        //Pokemon04
        pk4.getAbilities().add(ay7);
        pk4.getAbilities().add(ay8);
        pk4.getTypes().add(t12);
        pk4.getEvolutions().add(ev7);
        pk4.getEvolutions().add(ev8);
        s.update(pk4);
        
        ay7.getPokemons().add(pk4);
        ay8.getPokemons().add(pk4);
        s.update(ay7);
        s.update(ay8);
        
        t12.getPokemons().add(pk4);
        s.update(t12);
        
        ev7.setPokemon(pk4);
        ev7.getTypes().add(t12);
        s.update(ev7);
        
        ev8.setPokemon(pk4);
        ev8.getTypes().add(t12);
        ev8.getTypes().add(t10);
        s.update(ev8);
        
        t12.getEvolutions().add(ev7);
        t12.getEvolutions().add(ev8);
        s.update(t12);
        
        t10.getEvolutions().add(ev8);
        s.update(t10);
        
        //Pokemon05
        pk5.getAbilities().add(ay7);
        pk5.getAbilities().add(ay8);        
        pk5.getTypes().add(t12);
        pk5.getTypes().add(t8);
        pk5.getEvolutions().add(ev9);
        pk5.getEvolutions().add(ev10);
        s.update(pk5);
        
        ay7.getPokemons().add(pk5);
        ay8.getPokemons().add(pk5);
        s.update(ay7);
        s.update(ay8);
        
        t12.getPokemons().add(pk5);
        s.update(t12);
        
        t8.getPokemons().add(pk5);
        s.update(t8);
        
        ev9.setPokemon(pk5);
        ev9.getTypes().add(t12);
        ev9.getTypes().add(t8);
        s.update(ev9);
        
        ev10.setPokemon(pk5);
        ev10.getTypes().add(t12);
        ev10.getTypes().add(t8);
        s.update(ev10);
        
        t12.getEvolutions().add(ev9);
        t12.getEvolutions().add(ev10);
        s.update(t12);
        
        t8.getEvolutions().add(ev9);
        t8.getEvolutions().add(ev10);
        s.update(t8);
        
        //Pokemon06
        pk6.getAbilities().add(ay9);
        pk6.getAbilities().add(ay10);
        pk6.getTypes().add(t1);
        pk6.getTypes().add(t10);
        pk6.getEvolutions().add(ev11);
        pk6.getEvolutions().add(ev12);
        s.update(pk6);
        
        ay9.getPokemons().add(pk6);
        ay10.getPokemons().add(pk6);
        s.update(ay9);
        s.update(ay10);
        
        t1.getPokemons().add(pk6);
        s.update(t1);
        
        t10.getPokemons().add(pk6);
        s.update(t10);
        
        ev11.setPokemon(pk6);
        ev11.getTypes().add(t1);
        ev11.getTypes().add(t10);
        s.update(ev11);
        
        ev12.setPokemon(pk6);
        ev12.getTypes().add(t1);
        ev12.getTypes().add(t10);
        s.update(ev12);
        
        t1.getEvolutions().add(ev11);
        t1.getEvolutions().add(ev12);
        s.update(t1);
        
        t10.getEvolutions().add(ev11);
        t10.getEvolutions().add(ev12);
        s.update(t10);

        //Pokemon07
        pk7.getAbilities().add(ay8);
        pk7.getAbilities().add(ay11);
        pk7.getAbilities().add(ay12);
        pk7.getTypes().add(t1);
        pk7.getEvolutions().add(ev13);
        s.update(pk7);
        
        ay8.getPokemons().add(pk7);
        ay11.getPokemons().add(pk7);
        ay12.getPokemons().add(pk7);
        s.update(ay8);
        s.update(ay11);
        s.update(ay12);
        
        t1.getPokemons().add(pk7);
        s.update(t1);
        
        ev13.setPokemon(pk7);
        ev13.getTypes().add(t1);
        s.update(ev13);
        
        t1.getEvolutions().add(ev13);
        s.update(t1);
        
        //Pokemon08
        pk8.getAbilities().add(ay9);
        pk8.getAbilities().add(ay13);
        pk8.getTypes().add(t1);
        pk8.getTypes().add(t10);
        pk8.getEvolutions().add(ev14);
        s.update(pk8);
        
        ay9.getPokemons().add(pk8);
        ay13.getPokemons().add(pk8);
        s.update(ay9);
        s.update(ay13);
        
        t1.getPokemons().add(pk8);
        s.update(t1);
        
        t10.getPokemons().add(pk8);
        s.update(t10);
        
        ev14.setPokemon(pk8);
        ev14.getTypes().add(t1);
        ev14.getTypes().add(t10);
        s.update(ev14);
        
        t1.getEvolutions().add(ev14);
        s.update(t1);
        
        t10.getEvolutions().add(ev14);
        s.update(t10);
        
        //Pokemon09
        pk9.getAbilities().add(ay14);
        pk9.getAbilities().add(ay15);
        pk9.getAbilities().add(ay16);
        pk9.getTypes().add(t8);
        pk9.getEvolutions().add(ev15);
        s.update(pk9);
        
        ay14.getPokemons().add(pk9);
        ay15.getPokemons().add(pk9);
        ay16.getPokemons().add(pk9);
        s.update(ay14);
        s.update(ay15);
        s.update(ay16);
        
        t8.getPokemons().add(pk9);
        s.update(t8);
        
        ev15.setPokemon(pk9);
        ev15.getTypes().add(t8);
        s.update(ev15);
        
        t8.getEvolutions().add(ev15);
        s.update(t8);
        
        //Pokemon10
        pk10.getAbilities().add(ay17);
        pk10.getAbilities().add(ay18);
        pk10.getTypes().add(t9);
        pk10.getEvolutions().add(ev16);
        s.update(pk10);
        
        ay17.getPokemons().add(pk10);
        ay18.getPokemons().add(pk10);
        s.update(ay17);
        s.update(ay18);
        
        t9.getPokemons().add(pk10);
        s.update(t9);
        
        ev16.setPokemon(pk10);
        ev16.getTypes().add(t9);
        s.update(ev16);
        
        t9.getEvolutions().add(ev16);
        s.update(t9);
        
        //Pokemon11
        pk11.getAbilities().add(ay19);
        pk11.getAbilities().add(ay20);
        pk11.getAbilities().add(ay21);
        pk11.getTypes().add(t12);
        pk11.getTypes().add(t5);
        pk11.getEvolutions().add(ev17);
        s.update(pk11);
        
        ay19.getPokemons().add(pk11);
        ay20.getPokemons().add(pk11);
        ay21.getPokemons().add(pk11);
        s.update(ay19);
        s.update(ay20);
        s.update(ay21);
        
        t12.getPokemons().add(pk11);
        s.update(t12);
        
        t5.getPokemons().add(pk11);
        s.update(t5);
        
        ev17.setPokemon(pk11);
        ev17.getTypes().add(t12);
        ev17.getTypes().add(t5);
        s.update(ev17);
        
        t12.getEvolutions().add(ev17);
        s.update(t12);
        
        t5.getEvolutions().add(ev17);
        s.update(t5);
        
        t.commit();
        s.close();
        SessionManager.closeSessionFactory();  
    }
}