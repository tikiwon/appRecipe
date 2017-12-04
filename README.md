Activity_Drawer
    //inicia a activity
    protected void onCreate(Bundle savedInstanceState)

    //cria o menu drawer
    public void onBackPressed()

    //instancia as opções do menu drawer
    public boolean onCreateOptionsMenu(Menu menu)

    //recebe a interação do menu drawer
    public boolean onOptionsItemSelected(MenuItem item)

    //verifica o menu clicado e faz o direcionamento da tela
    public boolean onNavigationItemSelected(MenuItem item)

    //Intent para abrir outra tela
    private void abrirTelaPrincipalActivity()

CadastroUsuarioActivity
    //inicia a activity
    protected void onCreate(Bundle savedInstanceState)

    //Valida dados e chama outros métodos
    private void validaDadosCadastro()

    //prepara a tarefa de atutenticação de usuário
    private void cadastrarUsuario()

    //envia para o firebase e trata erros
    private boolean insereUsuario(Usuario usuario)

    //direcionador para a tela de login
    private void abrirTelaLogin()

PrincipalActivity
    //inicia a activity
    protected void onCreate(Bundle savedInstanceState)

    //valida os dados do login
    private void validarLogin()

    //validação básica de campos
    private void  validaCamposLogin()

    //instância o menu drawer
    private void abrirTelaDrawer()

    //direcionamento de tela
    private void abrirTelaCadastro()


Classes > Mural: public void setTexto(String texto)
Classes > Usuario: getter e setter  id, email, senha e apelido)

DAO > ConfiguracaoFirebase
    //possui as instruções para referenciar o firebase
    public static DatabaseReference getFirebase()

    //verifica se o metodo autenticação não está vazio
    public static FirebaseAuth getFirebaseAuth()

Fragments

- SendFragment
       //infla o botão enviar e fica aguardando intenção do usuário
       public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState)

       //valida se o campo de mensagem está preenchido e chama o enviaDadosSolicitacaoReceita()
       private void validaPreenchimentoCampos()

       //instancia o objeto mural e seta os valores e faz o envio e tratamento de erro do firebase
       private void enviaDadosSolicitacaoReceita()
       

![alt text](https://cdn57.androidauthority.net/wp-content/uploads/2017/05/android-studio-logo-840x359.png)


      
Diagrama de sequência
![alt text](https://imgur.com/a/QODLh.png)


Diagrama de classe
![alt text](https://i.imgur.com/j1zu8jK.png)
