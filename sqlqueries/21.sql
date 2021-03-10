SELECT city.name, country.name, city.population
FROM city JOIN country ON city.id = country.capital
WHERE continent = 'Europe'
ORDER BY Population DESC
LIMIT n
;
