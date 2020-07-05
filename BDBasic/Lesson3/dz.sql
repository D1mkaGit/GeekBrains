# База данных «Страны и города мира»:
# 1. Сделать запрос, в котором мы выберем все данные о городе – регион, страна.
SELECT 
    _cities.title AS City,
    _regions.title AS Region,
    _countries.title AS Country
FROM
    geodata._cities
        JOIN
    (_countries, _regions) ON (_cities.region_id = _regions.id
        AND _cities.country_id = _countries.id)
WHERE
    _cities.title LIKE 'Москва'; 
    
# 2. Выбрать все города из Московской области.
SELECT 
    _cities.title
FROM
    _cities
WHERE
    region_id = '1053480';-- селект через ид
SELECT 
    _cities.title
FROM
    _regions
        JOIN
    _cities ON _cities.region_id = _regions.id
WHERE
    _regions.title LIKE 'Московск%';-- селект через джойн
SELECT 
    title
FROM
    _cities
WHERE
    region_id IN (SELECT 
            id
        FROM
            _regions
        WHERE
            title LIKE 'Московск%'); -- селект через запрос внутри запроса
            
# База данных «Сотрудники»:
# 1. Выбрать среднюю зарплату по отделам.
SELECT 
    departments.dept_name AS Departamanet,
    AVG(salaries.salary) AS 'Average Salary By Dep'
FROM
    dept_emp
        JOIN
    departments ON dept_emp.dept_no = departments.dept_no
        JOIN
    salaries ON dept_emp.emp_no = salaries.emp_no
GROUP BY Departamanet;

# 2. Выбрать максимальную зарплату у сотрудника.
SELECT 
    MAX(salary),
    CONCAT(employees.last_name,
            ' ',
            employees.first_name) AS 'name'
FROM
    salaries
        JOIN
    employees ON salaries.emp_no = employees.emp_no;
    
# 3. Удалить одного сотрудника, у которого максимальная зарплата.
DELETE FROM employees 
WHERE
    emp_no = (SELECT 
        emp_no
    FROM
        salaries
    
    WHERE
        salary = (SELECT 
            MAX(salary)
        FROM
            salaries));
            
# 4. Посчитать количество сотрудников во всех отделах.
SELECT 
    departments.dept_name AS Departamanet,
    COUNT(dept_emp.emp_no) AS 'Count'
FROM
    dept_emp
        JOIN
    departments ON dept_emp.dept_no = departments.dept_no
GROUP BY Departamanet;

# 5. Найти количество сотрудников в отделах и посмотреть, сколько всего денег получает отдел.
SELECT 
    departments.dept_name AS Departamanet,
    COUNT(DISTINCT dept_emp.emp_no) AS 'Emp Count',
    SUM(salaries.salary) AS 'Sum Salary'
FROM
    dept_emp
        JOIN
    departments ON dept_emp.dept_no = departments.dept_no
        JOIN
    salaries ON dept_emp.emp_no = salaries.emp_no
GROUP BY Departamanet;