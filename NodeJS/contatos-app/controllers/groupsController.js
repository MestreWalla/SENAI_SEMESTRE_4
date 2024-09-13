import Group from '../models/Group';

export const getGroups = async (req, res) => {
  try {
    const groups = await Group.find({});
    res.status(200).json({ success: true, data: groups });
  } catch (error) {
    res.status(400).json({ success: false, error: error.message });
  }
};

export const createGroup = async (req, res) => {
  try {
    const group = await Group.create(req.body);
    res.status(201).json({ success: true, data: group });
  } catch (error) {
    res.status(400).json({ success: false, error: error.message });
  }
};

export const getGroupById = async (req, res) => {
  const { id } = req.query;

  try {
    const group = await Group.findById(id);
    if (!group) {
      return res.status(404).json({ success: false, message: 'Group not found' });
    }
    res.status(200).json({ success: true, data: group });
  } catch (error) {
    res.status(400).json({ success: false, error: error.message });
  }
};

export const updateGroup = async (req, res) => {
  const { id } = req.query;

  try {
    const group = await Group.findByIdAndUpdate(id, req.body, {
      new: true,
      runValidators: true,
    });
    if (!group) {
      return res.status(404).json({ success: false, message: 'Group not found' });
    }
    res.status(200).json({ success: true, data: group });
  } catch (error) {
    res.status(400).json({ success: false, error: error.message });
  }
};

export const deleteGroup = async (req, res) => {
  const { id } = req.query;

  try {
    const deletedGroup = await Group.deleteOne({ _id: id });
    if (!deletedGroup.deletedCount) {
      return res.status(404).json({ success: false, message: 'Group not found' });
    }
    res.status(200).json({ success: true, data: {} });
  } catch (error) {
    res.status(400).json({ success: false, error: error.message });
  }
};
