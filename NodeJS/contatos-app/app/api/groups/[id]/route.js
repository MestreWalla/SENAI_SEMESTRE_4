import dbConnect from '../../../utils/dbConnect';
import Group from '../../../models/Group';

export default async function handler(req, res) {
  await dbConnect();

  const { method } = req;
  const { id } = req.query;

  switch (method) {
    case 'GET': // Obter um grupo específico
      try {
        const group = await Group.findById(id);
        if (!group) {
          return res.status(404).json({ success: false });
        }
        res.status(200).json({ success: true, data: group });
      } catch (error) {
        res.status(400).json({ success: false });
      }
      break;
    case 'PUT': // Modificar um grupo específico
      try {
        const group = await Group.findByIdAndUpdate(id, req.body, {
          new: true,
          runValidators: true,
        });
        if (!group) {
          return res.status(404).json({ success: false });
        }
        res.status(200).json({ success: true, data: group });
      } catch (error) {
        res.status(400).json({ success: false });
      }
      break;
    case 'DELETE': // Excluir um grupo específico
      try {
        const deletedGroup = await Group.deleteOne({ _id: id });
        if (!deletedGroup) {
          return res.status(404).json({ success: false });
        }
        res.status(200).json({ success: true, data: {} });
      } catch (error) {
        res.status(400).json({ success: false });
      }
      break;
    default:
      res.status(400).json({ success: false });
      break;
  }
}
