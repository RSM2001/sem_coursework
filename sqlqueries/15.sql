--All the capital cities in a region organised by largest to smallest.
SELECT city.name, country.name, city.population 
FROM city JOIN country ON city.id=country.capital
WHERE region = 'Western Europe'
ORDER BY population DESC
;
