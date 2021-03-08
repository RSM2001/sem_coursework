--The top N populated capital cities in the world where N is provided by the user.
SELECT city.name, country.name, city.population
FROM city JOIN country ON city.id=country.capital
ORDER BY population DESC        
Limit n 
;