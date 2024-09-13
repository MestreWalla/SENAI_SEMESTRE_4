import dbConnect from '../../../utils/dbConnect';
import { getGroupById, updateGroup, deleteGroup } from '../../../controllers/groupsController';

export default async function handler(req, res) {
  await dbConnect();
  const { method } = req;
  const { id } = req.query;

  switch (method) {
    case 'GET':
      await getGroupById(req, res);
      break;
    case 'PUT':
      await updateGroup(req, res);
      break;
    case 'DELETE':
      await deleteGroup(req, res);
      break;
    default:
      res.setHeader('Allow', ['GET', 'PUT', 'DELETE']);
      res.status(405).end(`Method ${method} Not Allowed`);
  }
}
