import dbConnect from '../../../utils/dbConnect';
import Contact from '../../../models/Contact';

export default async function handler(req, res) {
  await dbConnect();

  const { method } = req;
  const { id } = req.query;

  switch (method) {
    case 'GET': // Obter um contato específico
      try {
        const contact = await Contact.findById(id);
        if (!contact) {
          return res.status(404).json({ success: false });
        }
        res.status(200).json({ success: true, data: contact });
      } catch (error) {
        res.status(400).json({ success: false });
      }
      break;
    case 'PUT': // Modificar um contato específico
      try {
        const contact = await Contact.findByIdAndUpdate(id, req.body, {
          new: true,
          runValidators: true,
        });
        if (!contact) {
          return res.status(404).json({ success: false });
        }
        res.status(200).json({ success: true, data: contact });
      } catch (error) {
        res.status(400).json({ success: false });
      }
      break;
    case 'DELETE': // Excluir um contato específico
      try {
        const deletedContact = await Contact.deleteOne({ _id: id });
        if (!deletedContact) {
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