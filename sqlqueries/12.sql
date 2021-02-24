--The top N populated cities in the world
SELECT city.name, country.name, city.district, city.population
FROM city JOIN country ON city.countrycode=country.code
ORDER BY population DESC
LIMIT n 
;