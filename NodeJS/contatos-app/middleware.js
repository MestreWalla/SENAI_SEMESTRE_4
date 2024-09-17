// middleware.js

import { NextResponse } from "next/server";

export function middleware(req) {
  // Acesso ao cookie usando a função get()
  const token = req.cookies.get("auth-token") || null;

  console.log("Token encontrado:", token); // Log do token

  if (token) {
    console.log("Token válido. Permitindo acesso.");
    return NextResponse.next();
  }

  console.log("Token não encontrado. Redirecionando para login.");
  return NextResponse.redirect(new URL("/login", req.url));
}

export const config = {
  matcher: ["/agenda/:path*"],
};
