--All the cities in a continent organised by largest population to smallest.
SELECT city.name, country.name, city.district, city.population
FROM city
JOIN country
    ON city.countryCode = country.code
WHERE continent = 'Europe'
ORDER BY population DESC
;
