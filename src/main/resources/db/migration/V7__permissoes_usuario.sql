-- Permissões que o código já exige em @PreAuthorize, mas que nunca foram cadastradas
-- (sem isto até o admin recebe 403 em clientes, culturas, usuários...).
INSERT INTO permissao (ativo, descricao)
SELECT true, v.descricao
FROM (VALUES
    ('ROLE_PESQUISAR_CLIENTE'),
    ('ROLE_CADASTRAR_CLIENTE'),
    ('ROLE_PESQUISAR_CULTURA'),
    ('ROLE_CADASTRAR_CULTURA'),
    ('ROLE_PESQUISAR_CULTURA_CLIENTE'),
    ('ROLE_CADASTRAR_CULTURA_CLIENTE'),
    ('ROLE_PESQUISAR_USUARIO'),
    ('ROLE_CADASTRAR_USUARIO'),
    ('ROLE_PESQUISAR_PERMISSAO')
) AS v(descricao)
WHERE NOT EXISTS (SELECT 1 FROM permissao p WHERE p.descricao = v.descricao);

-- O administrador passa a ter todas as permissões ativas.
INSERT INTO usuario_permissao (usuario_id, permissao_id)
SELECT u.id, p.id
FROM usuario u
CROSS JOIN permissao p
WHERE u.email = 'admin@estacao.com'
  AND p.ativo = true
  AND NOT EXISTS (
      SELECT 1 FROM usuario_permissao up
      WHERE up.usuario_id = u.id AND up.permissao_id = p.id
  );

-- E-mail é o login: não pode repetir.
CREATE UNIQUE INDEX IF NOT EXISTS ux_usuario_email ON usuario (email);
