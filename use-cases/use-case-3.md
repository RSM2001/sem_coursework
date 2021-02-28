# USE CASE: 3 Produce several reports regarding capital cities

## CHARACTERISTIC INFORMATION

### Goal in Context

As a geography teacher I want to be able to see a list of the capital cities in the world, 
in a continent and in a region, all organised by largest population to smallest.
I also want to be able to see the top N populated capital cities in the world, in a
continent and in a region,where N is provided by me, so my students can analyse the 
differences in the population density of capital cities.

### Scope

*what system is considered black-box under design*

### Level

*one of: Summary, Primary task, subfunction*

### Preconditions

We know the role. Database contains population information about countries, cities, capital cities and languages.

### Success End Condition

The six reports are available for the teacher to provide to their students.

### Failed End Condition

No reports are produced.

### Primary Actor

Geography Teacher.

### Trigger

*the action upon the system that starts the use case, may be a time event*

## MAIN SUCCESS SCENARIO

1. Geography teacher defines N.
2. Geography teacher extracts the capital city information.
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