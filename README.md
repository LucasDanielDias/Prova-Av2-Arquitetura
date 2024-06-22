# AV2 Prova Aramuni
Este é um sistema criado para a criação e validação de usuarios em um sistema pelo JWT.
Segue a baixo o que fiz.

## O que fiz

- **Login**: Geração de token JWT ao efetuar login.
- **Buscar nome**: Retorna o nome do usuario através do token.
- **Buscar ADMIN**: Retorna nome do usuario através do token enviado, apenas usuarios ADMIN
- **Buscar GERENTE**: Retorna nome do usuario através do token enviado, apenas usuarios GERENTE

- ### Configuração de Segurança(`SecurityConfig`)

```java
@Configuration
@EnableWebSecurity
public class SecurityConfig {
    // Configurações de segurança
}
```
### Controller (`Controller`)
```java
@RestController
public class UsuarioController {
    // Endpoints de login,buscar nome etc.
}
```
## Arquitetura do projeto:

![image](https://github.com/LucasDanielDias/Prova-Av2-Arquitetura/blob/c98c57db25724308bdf15007f6087aee43e969af/imagem/Imagem%202.png)

 - Como podemos ver a arquitetura do projeto foi feito a partir do passo dos controlers.
 - Seguindo para a utilização dos micro services definidos pelos controlers.
 - Os Micro services passam assim o fluxo da aplicação parao login e a senha.
 - Modulando as URL's seguiremos para a pagina de login que estará associado ao nivel de segurança definido pelo security.
 - o nivel de segurança é associado as suas configurações e eles validam que tipo de usuario vocêpode acessar.
 - usuario para cliente
 - gerente para gerente de produtos
 - adim para administradores

## Endpoints (caso queira recriar)

  @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/login")
    public String login(@RequestBody UsuarioEntity usuario) {
        String token = usuarioService.generateToken(usuario.getNome());
        return "Token: " + token;
    }


    @GetMapping("/nomeusuario/{token}")
    public String extractUsername(@PathVariable String token) {
        String username = usuarioService.extractUsername(token);
        return username;
    }

    @Secured("GERENTE")
    @GetMapping(value = "/gerente/{token}")
    public String buscaGerente(@PathVariable String token) {
        System.out.println("Chegou aqui controller");
        String nomeUsuario = usuarioService.extractUsername(token);
        return "Gerente: " + nomeUsuario;
    }

    @Secured("ADMIN")
    @GetMapping(value = "/admin/{token}")
    public String buscaAdmin(@PathVariable String token) {
        System.out.println("Chegou aqui controller");
        String nomeUsuario = usuarioService.extractUsername(token);
        return "Admin: " + nomeUsuario;
    }

 ## Meu model (caso queira recriar)
public class UsuarioEntity {

    private Long id;
    private String nome;
    private String email;
    private String senha;

    public UsuarioEntity(Long id, String nome, String email, String senha) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
## Contato
    -Email: lucaas.daniel2003@gmail.com
