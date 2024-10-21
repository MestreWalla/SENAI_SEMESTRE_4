# Manual do Usuário: Sistema de Manutenção

Este manual foi elaborado para auxiliar usuários comuns na utilização do Sistema de Manutenção. Ele abrange as principais funcionalidades do sistema, com instruções passo a passo e ilustrações para facilitar a compreensão.

## Tela Principal

**Figura 1: Tela Principal do Sistema de Manutenção.**  
![Comentario](https://github.com/MestreWalla/SENAI_SEMESTRE_4/blob/main/Java/projeto_sistema_manutencao/ImagensManual/Manutencoes.png)

Ao iniciar o sistema, você se deparará com a tela principal. Ela é composta por abas que organizam as diferentes funcionalidades do sistema, como "Manutenção", "Máquinas", "Falhas" e "Técnicos".

## Gerenciamento de Máquinas

A aba "Máquinas" permite gerenciar todas as máquinas cadastradas no sistema.
 ![INSERIR IMAGEM DA ABA "MÁQUINAS" AQUI](https://github.com/MestreWalla/SENAI_SEMESTRE_4/blob/main/Java/projeto_sistema_manutencao/ImagensManual/Maquinas.png) 

### Funcionalidades:

- **Cadastrar Máquina**: Clique no botão "Cadastrar Máquina" para adicionar uma nova máquina ao sistema. Preencha os campos do formulário com as informações da máquina, como código, nome, fabricante, modelo, data de aquisição, tempo de vida estimado, localização e detalhes.

![Cadastrar Maquina](https://github.com/MestreWalla/SENAI_SEMESTRE_4/blob/main/Java/projeto_sistema_manutencao/ImagensManual/CadastroMaquina.png)
  
- **Editar Máquina**: Para editar as informações de uma máquina, selecione a máquina desejada na tabela e clique no botão "Editar Máquina". As informações da máquina serão carregadas no formulário de edição.

  ![Editar Maquina](https://github.com/MestreWalla/SENAI_SEMESTRE_4/blob/main/Java/projeto_sistema_manutencao/ImagensManual/EditarMaquina.png)
  
- **Excluir Máquina**: Para excluir uma máquina, selecione a máquina desejada na tabela e clique no botão "Excluir Máquina". Confirme a exclusão na caixa de diálogo que aparecerá.

![Excluir Maquina](https://github.com/MestreWalla/SENAI_SEMESTRE_4/blob/main/Java/projeto_sistema_manutencao/ImagensManual/ExcluirMaquina.png)
  
- **Atualizar Lista**: Para atualizar a lista de máquinas, clique no botão "Atualizar Lista".
  
- **Exportar para PDF**: Para gerar um relatório em PDF com a lista de máquinas, clique no botão "Exportar para PDF".

![Exportar para PDF](https://github.com/MestreWalla/SENAI_SEMESTRE_4/blob/main/Java/projeto_sistema_manutencao/ImagensManual/ExportarPDF.png)

## Gerenciamento de Manutenções

Na aba "Manutenção", você pode gerenciar todas as manutenções realizadas nas máquinas.  
**Figura 5: Aba "Manutenção" do Sistema de Manutenção.**  
![Manutençòes] (https://github.com/MestreWalla/SENAI_SEMESTRE_4/blob/main/Java/projeto_sistema_manutencao/ImagensManual/Manutencoes.png)

### Funcionalidades:

- **Cadastrar Manutenção**: Clique no botão "Cadastrar Manutenção" para registrar uma nova manutenção. Selecione a máquina, o técnico responsável, preencha a data, o tipo de manutenção, as peças trocadas, o tempo de parada e as observações ![INSERIR IMAGEM DO FORMULÁRIO DE CADASTRO DE MANUTENÇÃO AQUI].
  
- **Editar Manutenção**: Para editar uma manutenção, selecione a manutenção desejada na tabela e clique no botão "Editar Manutenção".
  
- **Excluir Manutenção**: Para excluir uma manutenção, selecione a manutenção desejada na tabela e clique no botão "Excluir Manutenção".
  
- **Atualizar Lista**: Para atualizar a lista de manutenções, clique no botão "Atualizar Lista".
  
- **Exportar para PDF**: Para gerar um relatório em PDF com a lista de manutenções, clique no botão "Exportar para PDF".

## Gerenciamento de Falhas

A aba "Falhas" permite o gerenciamento de falhas reportadas nas máquinas.  
**Figura 7: Aba "Falhas" do Sistema de Manutenção.**  
![INSERIR IMAGEM DA ABA "FALHAS" AQUI]

### Funcionalidades:

- **Cadastrar Falha**: Clique no botão "Cadastrar Falha" para registrar uma nova falha. Selecione a máquina, o técnico que atendeu a falha, a data da ocorrência, descreva o problema, a prioridade e o operador responsável pela máquina no momento da falha ![INSERIR IMAGEM DO FORMULÁRIO DE CADASTRO DE FALHA AQUI].
  
- **Editar Falha**: Para editar as informações de uma falha, selecione a falha desejada na tabela e clique no botão "Editar Falha".
  
- **Excluir Falha**: Para excluir uma falha, selecione a falha desejada na tabela e clique no botão "Excluir Falha".
  
- **Atualizar Lista**: Clique no botão "Atualizar Lista" para atualizar a lista de falhas.
  
- **Exportar para PDF**: Para gerar um relatório em PDF com a lista de falhas, clique no botão "Exportar para PDF".

## Gerenciamento de Técnicos

A aba "Técnicos" possibilita o gerenciamento dos técnicos responsáveis pelas manutenções.  
**Figura 9: Aba "Técnicos" do Sistema de Manutenção.**  
![INSERIR IMAGEM DA ABA "TÉCNICOS" AQUI]

### Funcionalidades:

- **Cadastrar Técnico**: Clique no botão "Cadastrar Técnico" para adicionar um novo técnico ao sistema. Preencha o nome, a especialidade e a disponibilidade do técnico ![INSERIR IMAGEM DO FORMULÁRIO DE CADASTRO DE TÉCNICO AQUI].
  
- **Editar Técnico**: Para editar as informações de um técnico, selecione o técnico desejado na tabela e clique no botão "Editar Técnico".
  
- **Excluir Técnico**: Para excluir um técnico, selecione o técnico desejado na tabela e clique no botão "Excluir Técnico".
  
- **Atualizar Lista**: Para atualizar a lista de técnicos, clique no botão "Atualizar Lista".
  
- **Exportar para PDF**: Para gerar um relatório em PDF com a lista de técnicos, clique no botão "Exportar para PDF".

## Observações

- O Sistema de Manutenção foi desenvolvido utilizando a linguagem de programação **Java** e a biblioteca gráfica **Swing**.
- O sistema se conecta a uma **API JSON** para armazenar e recuperar os dados.
- A interface gráfica utiliza o tema visual **"Nimbus Look and Feel"** para uma melhor experiência do usuário.
- Os ícones utilizados nas abas são carregados a partir de arquivos de imagem.
- Os relatórios em **PDF** são gerados utilizando a biblioteca **iText**.
- A exclusão de dados exige confirmação do usuário para evitar operações acidentais.
- O sistema realiza a validação de dados para garantir a integridade das informações.

## Suporte

Em caso de dúvidas ou problemas na utilização do sistema, entre em contato com a equipe de suporte técnico.
