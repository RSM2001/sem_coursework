--All the capital cities in the world organised by largest population to smallest.
    SELECT city.name, country.name, city.population FROM city JOIN country ON city.id=country.capital
    ORDER BY population DESC
