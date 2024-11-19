## Ejemplos de Consultas  
  
**Verificar Festivos:** http://localhost:8081/api/festivos/validar?dia=12&mes=05&año=2024  
**Verificar Pascua:** http://localhost:8081/api/festivos/pascua?año=2025
  
  
**Pruebas de Seguridad:** http://localhost:8081/api/usuarios/login/M.Pardo/p123  
*Usuario:*  
INSERT INTO Usuario (id, clave, nombre, roles, usuario)
VALUES (1, 'p123', 'Miguel Pardo Alvarez',null, 'M.Pardo');
