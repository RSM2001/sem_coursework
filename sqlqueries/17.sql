--The top N populated cities in a region where N is provided by the user.
    SELECT city.name, country.name, city.district, city.population FROM city JOIN country ON city.countryCode=country.code
        WHERE region = 'example'
    ORDER BY population DESC
    LIMIT n 
