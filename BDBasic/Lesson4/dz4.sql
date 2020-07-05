-- Создать на основе запросов, которые вы сделали в ДЗ к уроку 3, VIEW.
USE `employees`;
CREATE OR REPLACE VIEW `max_salary_view` AS
    SELECT 
        MAX(salary),
        CONCAT(employees.last_name,
                ' ',
                employees.first_name) AS 'name'
    FROM
        salaries
            JOIN
        employees ON salaries.emp_no = employees.emp_no;
-- код для проверки вьюшки:
-- select * FROM max_salary_view;

-- Создать функцию, которая найдет менеджера по имени и фамилии.
USE `employees`;
DROP procedure IF EXISTS `get_manager_procedure`;

DELIMITER $$
USE `employees`$$
CREATE PROCEDURE `get_manager_procedure` (fn CHAR (150), ln CHAR (150))
BEGIN
SELECT 
    CONCAT(e.last_name, ' ', e.first_name) AS 'Manager Name'
FROM
    employees AS e
WHERE
    e.emp_no = (SELECT 
            d.emp_no
        FROM
            dept_manager AS d
        WHERE
            dept_no = (SELECT 
                    d.dept_no
                FROM
                    employees.employees AS e
                        JOIN
                    dept_emp AS d ON e.emp_no = d.emp_no
                WHERE
                    e.first_name = fn AND e.last_name = ln
                        AND d.to_date = '9999-01-01')
                AND d.to_date = '9999-01-01');
END$$

DELIMITER ;
-- код для проверки процедуры (пример параметров 'Georgi','Facello'):
-- CALL get_manager_procedure('Georgi','Facello');

-- Создать триггер, который при добавлении нового сотрудника будет выплачивать ему вступительный бонус, занося запись в таблицу salary.
DROP TRIGGER IF EXISTS `employees`.`employees_AFTER_INSERT`;

DELIMITER $$
USE `employees`$$
CREATE DEFINER = CURRENT_USER TRIGGER `employees`.`employees_AFTER_INSERT` AFTER INSERT ON `employees` FOR EACH ROW
BEGIN
INSERT INTO employees.salaries
VALUES (NEW.emp_no, '5000', CURDATE(), CURDATE());
END$$
DELIMITER ;

-- код для проверки тригера:
-- INSERT INTO `employees`.`employees` (`emp_no`, `birth_date`, `first_name`, `last_name`, `gender`, `hire_date`) VALUES ('0', '1999-08-08', 'Test', 'Test', 'M', CURDATE());

