package com.example;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class UsuarioController {
    private List<Usuario> usuarios;
    private URL url;

    public UsuarioController() {
        usuarios = new ArrayList<>();
    }

    public void read() {
        try {
            // Estabelecer conexão
            url = new URL("http://localhost:3000/usuarios");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            // Verificar o status da conexão
            int status = conn.getResponseCode();
            if (status != 200) { // Se diferente de 200, lançar uma exceção
                throw new Exception("Erro de conexao");
            }
            // Gravar os dados da api na memória
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String linha;
            // Converter os dados em um arquivo de texto (String)
            StringBuffer content = new StringBuffer();
            // Ler os dados da api linha a linha
            while ((linha = br.readLine()) != null) {
                content.append(linha);
            }
            br.close(); // Fechar o buffer de leitura
            // Converter o arquivo de texto em uma lista de objetos Usuario
            JSONArray dadosUsuarios = new JSONArray(content.toString());
            // Converter cada objeto do JSON para um Usuario e adicionar à lista de usuários
            for (int i = 0; i < dadosUsuarios.length(); i++) {
                JSONObject usuarioJson = dadosUsuarios.getJSONObject(i);
                usuarios.add(new Usuario(
                        usuarioJson.getString("id"),
                        usuarioJson.getString("nome"),
                        usuarioJson.getInt("idade"),
                        usuarioJson.getString("endereco")));
            }
            // Mostrar os dados dos usuários
            for (Usuario usuario : usuarios) {
                System.out.println(usuario);
            }
        } catch (Exception e) {
            System.out.println("Erro ao ler dados do API: " + e.getMessage());
        }
    }

    public void write(Usuario usuario) {
        try {
            url = new URL("http://localhost:3000/usuarios");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json; utf-8");
            conn.setRequestProperty("Accept", "aplication/json");
            conn.setDoOutput(true);
            // Informações nescessarias para o post
            // Criando o objeto JSON com as informações do usuário
            JSONObject usuarioJson = new JSONObject();
            usuarioJson.put("nome", usuario.getNome());
            usuarioJson.put("idade", usuario.getIdade());
            usuarioJson.put("endereco", usuario.getEndereco());

            // Enviar os dados para a API
            try (BufferedWriter bw = new BufferedWriter(
                    new OutputStreamWriter(conn.getOutputStream(), "UTF-8"))) {
                bw.write(usuarioJson.toString());
                bw.flush();
            }

            // Verificar status da conexao
            int status = conn.getResponseCode();
            if (status == 201) { // Se 201, o usuário foi criado com sucesso
                System.out.println("Usuario criado com sucesso!");
            } else {
                System.out.println("Erro ao cadastrar usuario: " + status);
            }
        } catch (Exception e) {
            System.out.println("Erro ao gravar dados na API: " + e.getMessage());
        }
    }

    public void update(Usuario usuario) {
        try {
            url = new URL("http://localhost:3000/usuarios/" + usuario.getId());
            // Criando a conexão com a API
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("PUT");
            conn.setRequestProperty("Content-Type", "application/json; utf-8");
            conn.setRequestProperty("Accept", "aplication/json");
            conn.setDoOutput(true);
            // Criando o objeto JSON com as informações do usuário
            JSONObject usuarioJson = new JSONObject();
            usuarioJson.put("id", usuario.getId());
            usuarioJson.put("nome", usuario.getNome());
            usuarioJson.put("idade", usuario.getIdade());
            usuarioJson.put("endereco", usuario.getEndereco());
            // Enviando os dados para a API
            try (BufferedWriter bw = new BufferedWriter(
                    new OutputStreamWriter(conn.getOutputStream(), "UTF-8"))) {
                bw.write(usuarioJson.toString());
                bw.flush();
            }
            // Verificar status da conexão
            int status = conn.getResponseCode();
            if (status == 200) { // Se 200, o usuário foi atualizado com sucesso
                System.out.println("Usuario atualizado com sucesso!");
                read(); // Mostrar os dados dos usuários
            } else {
                throw new Exception("Erro ao atualizar usuario" + status);
            }
        } catch (JSONException e) {
            System.out.println("Erro ao converter objeto para JSON: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Erro ao atualizar usuario na API: " + e.getMessage());
        }
    }

    public void delete(String id) {
        try {
            url = new URL("http://localhost:3000/usuarios/" + id);
            // Criando a conexão com a API
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("DELETE");
            conn.setRequestProperty("Accept", "application/json");
            // Verificar status da conexão
            int status = conn.getResponseCode();
            if (status == 200 || status == 204) { // Se 200 ou 204, o usuário foi excluído com sucesso
                System.out.println("Usuario excluído com sucesso!");
                read(); // Mostrar os dados dos usuários
            } else {
                throw new Exception("Erro ao excluir usuario: " + status);
            }
        } catch (Exception e) {
            System.out.println("Erro ao deletar usuario na API: " + e.getMessage());
        }
    }
}
