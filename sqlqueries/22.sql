--The top N populated capital cities in a region where N is provided by the user.
SELECT city.name, 
    country.name, 
    city.population 
FROM city 
    JOIN country 
    ON city.id = country.capital
WHERE region = 'British Islands'
ORDER BY population DESC
LIMIT n 
;
