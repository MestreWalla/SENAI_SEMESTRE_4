import mongoose from "mongoose";
import bcrypt from "bcrypt";

const UserSchema = new mongoose.Schema({
  username: {
    type: String,
    required: true,
    unique: true,
  },
  password: {
    type: String,
    required: true,
  },
});

// Função para hash da senha antes de salvar o usuário
UserSchema.pre("save", async function (next) {
  // Verifica se a senha foi modificada
  if (!this.isModified("password")) {
    return next();
  }

  try {
    // Gera o salt e faz o hash da senha
    const salt = await bcrypt.genSalt(10);
    this.password = await bcrypt.hash(this.password, salt);
    console.log("Senha hasheada com sucesso:", this.password); // Verifica se a senha foi hasheada
    next();
  } catch (error) {
    console.error("Erro ao hashear a senha:", error);
    next(error); // Passa o erro para ser capturado
  }
});

// Comparar senha fornecida com a hash salva

UserSchema.methods.comparePassword = async function (candidatePassword) {
  return await bcrypt.compare(candidatePassword, this.password);
};

// Exportando o modelo User

const User = mongoose.models.User || mongoose.model("User", UserSchema);

export default User;
