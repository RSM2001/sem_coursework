# USE CASE: 2 Produce several reports regarding cities

## CHARACTERISTIC INFORMATION

### Goal in Context

As a geography teacher I want to be able to see a list of the cities in the world, in a continent, 
in a region, in a country and in a district, all organised by largest population to smallest.
I also want to be able to see the top N populated cities in the world, in a continent,
in a region, in a country and in a district, where N is provided by me.
Finally, I want to know the population of people, people living in cities, and people not living 
in cities in each continent, each region and each country so that my students 
can complete their urbanisation projects.

### Scope

*what system is considered black-box under design*

### Level

*one of: Summary, Primary task, subfunction*

### Preconditions

We know the role. Database contains population information about countries, cities, capital cities and languages.

### Success End Condition

The thirteen reports are available for the teacher to provide to their students.

### Failed End Condition

No reports are produced.

### Primary Actor

Geography Teacher.

### Trigger

*the action upon the system that starts the use case, may be a time event*

## MAIN SUCCESS SCENARIO

1. Geography teacher defines N.
2. Geography teacher extracts the city information.
3. Geography teacher passes the information on to their students.

## EXTENSIONS

*put here the extensions, one at a time, each referring to the step of the main scenario*

1. **Condition**: action of sub use case

## SUB-VARIATIONS

*put here the sub-variations that will cause eventual branching in the scenario

1. list of sub-variations

## SCHEDULE

**DUE DATE**: *date or release of deployment*

*any other schedule/staffing information you need*