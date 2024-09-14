import mongoose from "mongoose";

const DATABASE_URL = process.env.DATABASE_URL;

// Verificação
if (!DATABASE_URL) {
  throw new Error(
    "Variavel de ambiente DATABASE_URL não foi definida. Verifique o arquivo .env.local."
  );
}

const connectMongo = async () => {
  try {
    await mongoose.connect(DATABASE_URL, {
      useNewUrlParser: true,
      useUnifiedTopology: true,
      // Add other options as needed
    });
    console.log("Conexão ao MongoDB realizada com sucesso!");
  } catch (error) {
    console.error("Erro ao conectar ao MongoDB:", error);
    // Consider handling specific error types here
  }
};

export default connectMongo;
