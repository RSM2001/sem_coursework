--All the cities in a region organised by largest population to smallest
SELECT city.name, country.name, city.district, city.population
FROM city JOIN country ON city.countrycode=country.code
WHERE region = 'Eastern Europe'
ORDER BY population DESC
;