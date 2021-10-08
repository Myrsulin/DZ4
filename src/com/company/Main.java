package com.company;

import java.awt.*;
import java.util.Random;

public class Main {
    public static int bossHelth = 700;
    public static int bossDemage = 50;
    public static String bossDefenceType = "";
    public static String[] heroesAttackTypes = {"Physical", "Magical", "Kinetic", "Medic", "Golem", "Thor", "Lacky", "Berserk"};
    public static int[] heroesHelth = {290, 280, 250, 300, 400, 250,150, 200};
    public static int[] heroesDamages = {20, 25, 15, 0, 8, 25, 15, 30};
    public static int roundNumber = 0;
public static  Random random = new Random();

    public static void chooseDefence(){
        Random random = new Random();
        int randomIndex = random.nextInt(heroesAttackTypes.length);
        bossDefenceType = heroesAttackTypes[randomIndex];
        System.out.println(" boss chose: " + bossDefenceType);
    }


    public static void main(String[] args) {
        printStatistics();
        while (!isGameFinished()) {
            round();
        }
    }
    public static void round(){
        roundNumber++;
        chooseDefence();
        bossHits();
        heroesHit();
        Golem();
        Thor();
        Lycky();
        Berserk();
        printStatistics();
        medic();
    }
    public static boolean isGameFinished(){
        if (bossHelth <=0){
            System.out.println(" Heroes won!!!");
            return true;

        }
        boolean allHeroesDead = true;
        for (int i = 0; i < heroesHelth.length; i++) {
            if(heroesHelth[i] >0){
                allHeroesDead = false;
                break;
                
            }
            
        }
        if (allHeroesDead){
            System.out.println("boss won!!!");

        }
        /*if (heroesHelth[0] <=0 && heroesHelth[1] <=0 && heroesHelth[2] <=0){
            System.out.println("boss won!!!");
            return true;


        }
        return false;*/
        return allHeroesDead;
    }
    public static void Golem(){
        int allFive = bossDemage /5;
        int aLaveHiro = 0;
        if (heroesHelth[4] >0 ){
            for (int i = 0; i < heroesHelth.length; i++) {
                if (i == 4){
                    continue;
                }else if (heroesHelth[i] > 0 ){
                    aLaveHiro +=1;
                    heroesHelth[i]+=allFive;
                }
            }
            heroesHelth[4]-=aLaveHiro * bossDemage;
            System.out.println("Golem take " + (aLaveHiro * bossDemage));


        }

    }
    public static void Thor(){
        Random random =new Random();
        boolean b = random.nextBoolean();
        if (heroesHelth[5]> 0){
            if (!(b)){
                bossDemage = 50;
            }else if (b){
                bossDemage = 0;
                System.out.println("boss stun");
            }
        }
    }
    public static void Lycky(){
        Random random =new Random();
        boolean b = random.nextBoolean();
        if (heroesHelth[6]> 0){
            if (!(b)){
                heroesHelth[6] += bossDemage;
                System.out.println("Lacky use superability ");
            }
        }
    }
    public static void Berserk(){
        Random random = new Random();
        int Blok = random.nextInt(20)+1;
        if (heroesHelth[7]> 0){
            heroesHelth[7]+= Blok;
            bossHelth-=Blok;
            System.out.println("Blokk "+Blok);
        }
    }

    public static void printStatistics() {
        System.out.println("=========ROUND " + roundNumber);
        System.out.println("Boss helth " + bossHelth + "[" + bossDemage + "]");
        for (int i = 0; i < heroesHelth.length; i++) {
            System.out.println(heroesAttackTypes[i] + " helth; " + heroesHelth[i] + " [" + heroesDamages[i] + "]");


        }
        System.out.println("============================");


    }

    public static void bossHits() {
        for (int i = 0; i < heroesHelth.length; i++) {
            if (heroesHelth[i] > 0 && bossHelth > 0){
                if (heroesHelth[i] - bossDemage < 0){
                    heroesHelth[i] = 0;
                }else {
                    heroesHelth[i] = heroesHelth[i] - bossDemage;
                }
            }

        }



    }
public  static void medic(){
        int randomHero = random.nextInt(heroesDamages.length);
        if (randomHero == 3){
            medic();
        }else {
            int hp = random.nextInt(20) + 10;
            if (heroesHelth[3] > 0){
                if (heroesHelth[randomHero]<100&& heroesHelth[randomHero]>0){
                    heroesHelth[randomHero] = heroesHelth[randomHero] + hp;
                    System.out.println("Medic healed"+ heroesAttackTypes [randomHero]+"for:"+hp);
                }
            }
        }

}
    public static void heroesHit() {
        for (int i = 0; i < heroesDamages.length; i++) {
            if (heroesHelth[i] > 0 && bossHelth > 0) {
                if (bossDefenceType== heroesAttackTypes[i]) {

                    int coeff = random.nextInt(9) + 2; // 2,3,4,5,6,7,8,9,10
                    if (bossHelth - heroesDamages[i] * coeff < 0) {
                        bossHelth = 0;
                    } else {
                        bossHelth = bossHelth - heroesDamages[i] * coeff;
                    }
                    System.out.println(
                            "Critical Damage: " + heroesDamages[i] * coeff);
                } else {
                    if (bossHelth - heroesDamages[i] < 0) {
                        bossHelth = 0;
                    } else {
                        bossHelth = bossHelth - heroesDamages[i];
                    }
                }
            }
        }
    }}




