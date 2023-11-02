INSERT INTO worker(name,birthday,level,salary) VALUES('John Doe', '1990-05-15', 'Trainee', 800),('Jane Smith', '1985-03-20', 'Junior', 1200),('Robert Johnson', '1992-08-10', 'Middle', 2500),('Emily Davis', '1988-02-28', 'Middle', 2800),('Michael Wilson', '1983-11-15', 'Senior', 6000),('Sarah Brown', '1995-07-03', 'Trainee', 900),('David Lee', '1991-09-25', 'Junior', 1300),
    ('Lisa Anderson', '1987-04-12', 'Middle', 2700),
    ('William Clark', '1984-06-19', 'Senior', 6200),
    ('Olivia Hall', '1994-12-05', 'Middle', 2600);

INSERT INTO client (name) VALUES('Alice'),('Bob'),('Eve'),('Charlie'),('Daniel');

INSERT INTO project(client_id, start_date, finish_date) VALUES(1, '2023-01-15', '2024-03-20'),(1, '2023-05-10', '2024-02-28'),(2, '2023-07-20', '2024-01-10'),(2, '2023-03-01', '2023-08-15'),(3, '2023-06-05', '2024-06-04'),(3, '2023-02-10', '2023-12-31'),(4, '2023-09-01', '2024-09-30'),(4, '2023-04-15', '2023-11-30'),(5, '2023-08-20', '2024-07-15'),(5, '2023-11-01', '2024-03-31');
INSERT INTO project_worker(project_id, worker_id) VALUES(1, 1), (1, 2), (1, 3),(2, 4), (2, 5),(3, 6), (3, 7),(4, 8), (4, 9),(5, 10);
