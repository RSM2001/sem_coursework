SELECT DISTINCT country.name, sum(distinct country.population),  CONCAT(ROUND(sum(distinct city.population)/sum(distinct country.population)*100), '%'), CONCAT(ROUND((sum(distinct country.population)-sum(distinct city.population))/sum(distinct country.population)*100), '%')
FROM city JOIN country ON city.countrycode=country.code
GROUP BY name
;