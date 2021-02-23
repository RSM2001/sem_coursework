--All the cities in the world organised by largest population to smallest.
SELECT city.name, country.name, city.district, city.population
FROM city
JOIN country
    ON city.countryCode = country.code
ORDER BY population DESC
;
