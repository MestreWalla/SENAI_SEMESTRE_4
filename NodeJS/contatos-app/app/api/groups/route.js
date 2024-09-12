import dbConnect from '../../../utils/dbConnect';
import Group from '../../../models/Group';

export default async function handler(req, res) {
  await dbConnect();

  const { method } = req;

  switch (method) {
    case 'GET': // Listar todos os grupos
      try {
        const groups = await Group.find({});
        res.status(200).json({ success: true, data: groups });
      } catch (error) {
        res.status(400).json({ success: false });
      }
      break;
    case 'POST': // Criar um novo grupo
      try {
        const group = await Group.create(req.body);
        res.status(201).json({ success: true, data: group });
      } catch (error) {
        res.status(400).json({ success: false, error: error.message });
      }
      break;
    default:
      res.status(400).json({ success: false });
      break;
  }
}
