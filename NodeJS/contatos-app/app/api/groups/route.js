import dbConnect from '../../../utils/dbConnect';
import { getGroups, createGroup } from '../../../controllers/groupsController';

export default async function handler(req, res) {
  await dbConnect();
  const { method } = req;

  switch (method) {
    case 'GET':
      await getGroups(req, res);
      break;
    case 'POST':
      await createGroup(req, res);
      break;
    default:
      res.setHeader('Allow', ['GET', 'POST']);
      res.status(405).end(`Method ${method} Not Allowed`);
  }
}
