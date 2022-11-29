package com.example.healite.Model;

public class Questionnaire {
    private int age;                    // Question 1: 1 == 18-28, 2 == 29-39, 3 == 40-50, 4 == 51+
    private String gender;              // Question 2: What is your gender?
    private int mentalHealthRate;       // Question 3: How would you rate your mental health?
    private int physicalFitness;        // Question 4: How satisfied are you with your fitness activity?
    private int relationSatisfaction;   // Question 5: Do you feel satisfied with your relationships and family?
    private int wellRested;             // Question 6: Do you feel well-rested after waking up?
    private int sleepHours;             // Question 7: How many hours do you sleep per night?
    private int toughTime;              // Question 8: Are you going through tough times?
    private int change;

    public Questionnaire(int age, String gender, int mentalHealthRate, int physicalFitness, int relationSatisfaction, int wellRested,
                         int sleepHours, int toughTime, int change) {
        this.age = age;
        this.gender = gender;
        this.mentalHealthRate = mentalHealthRate;
        this.physicalFitness = physicalFitness;
        this.relationSatisfaction = relationSatisfaction;
        this.wellRested = wellRested;
        this.sleepHours = sleepHours;
        this.toughTime = toughTime;
        this.change = change;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getMentalHealthRate() {
        return mentalHealthRate;
    }

    public void setMentalHealthRate(int mentalHealthRate) {
        this.mentalHealthRate = mentalHealthRate;
    }

    public int getPhysicalFitness() {
        return physicalFitness;
    }

    public void setPhysicalFitness(int physicalFitness) {
        this.physicalFitness = physicalFitness;
    }

    public int getRelationSatisfaction() {
        return relationSatisfaction;
    }

    public void setRelationSatisfaction(int relationSatisfaction) {
        this.relationSatisfaction = relationSatisfaction;
    }

    public int getWellRested() {
        return wellRested;
    }

    public void setWellRested(int wellRested) {
        this.wellRested = wellRested;
    }

    public int getSleepHours() {
        return sleepHours;
    }

    public void setSleepHours(int sleepHours) {
        this.sleepHours = sleepHours;
    }

    public int getToughTime() {
        return toughTime;
    }

    public void setToughTime(int toughTime) {
        this.toughTime = toughTime;
    }

    public int getChange() {
        return change;
    }

    public void setChange(int change) {
        this.change = change;
    }
}
