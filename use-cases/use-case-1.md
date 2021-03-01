# USE CASE: 1 Produce several reports regarding countries 

## CHARACTERISTIC INFORMATION

### Goal in Context

As a geography teacher I want to be able to see a list of the countries in the world, in a continent and 
in a region, all organised by largest population to smallest.
I also want to be able to see the top N populated countries in the world, in a 
continent and in a region, where N is provided by me, so my students can graph this information.

### Scope

Geography Department.

### Level

Primary.

### Preconditions

We know the role. Database contains population information about countries, cities, capital cities and languages.

### Success End Condition

The six reports are available for the teacher to provide to their students.

### Failed End Condition

No reports are produced.

### Primary Actor

Geography Teacher.

### Trigger

The school term starts and assignments are handed out.

## MAIN SUCCESS SCENARIO

1. Geography teacher defines N.
2. Geography teacher extracts the country information.
3. Geography teacher passes the information on to their students.

## EXTENSIONS

1a. system can't find the sql file.
    1a1. system sends error message.

## SUB-VARIATIONS

None.

## SCHEDULE

Release 1.0