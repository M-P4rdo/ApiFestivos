## Ejemplos de Consultas  
  
**Verificar Festivos:** http://localhost:8081/api/festivos/validar/12/05/2024  
**Verificar Pascua:** http://localhost:8081/api/festivos/pascua/2025  
**Kistar festivos segun el año:** http://localhost:8081/api/festivos/listar/2023  
  
  
**Pruebas de Seguridad:** http://localhost:8081/api/usuarios/login/M.Pardo/p123  
*Usuario:*  
INSERT INTO Usuario (id, clave, nombre, roles, usuario)
VALUES (1, 'p123', 'Miguel Pardo Alvarez',null, 'M.Pardo');
