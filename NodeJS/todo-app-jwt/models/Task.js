import mongoose from "mongoose";

const TaskSchema = new mongoose.Schema({
    userId: {
        type: mongoose.Schema.Types.ObjectId,
        ref: 'User',
        required: true
    },
    title: {
        type: String,
        required: true
    },
    completed: {
        type: Boolean,
        default: false
    },
});

const User = mongoose.models.User || mongoose.model("User", TaskSchema);

export default Task;