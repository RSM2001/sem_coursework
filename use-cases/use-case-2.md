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

Geography Department.

### Level

Primary.

### Preconditions

We know the role. Database contains population information about countries, cities, capital cities and languages.

### Success End Condition

The thirteen reports are available for the teacher to provide to their students.

### Failed End Condition

No reports are produced.

### Primary Actor

Geography Teacher.

### Trigger

The school term starts and assignments are handed out.

## MAIN SUCCESS SCENARIO

1. Geography teacher defines N.
2. Geography teacher extracts the city information.
3. Geography teacher passes the information on to their students.

## EXTENSIONS

1a. system can't find the sql file.
    1a1. system sends error message.

## SUB-VARIATIONS

None.

## SCHEDULE

Release 1.0