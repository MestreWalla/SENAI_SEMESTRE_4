// app/api/auth/logout/route.js

export async function POST(req) {
  try {
    // Remove o cookie de autenticação
    return new Response(null, {
      status: 200,
      headers: {
        "Set-Cookie":
          "auth-token=; Max-Age=0; path=/; secure; samesite=strict;",
        "Content-Type": "application/json",
      },
    });
  } catch (error) {
    // Envia uma resposta JSON de erro
    return new Response(
      JSON.stringify({ error: `Erro ao fazer logout: ${error.message}` }),
      {
        status: 500,
        headers: {
          "Content-Type": "application/json",
        },
      }
    );
  }
}
