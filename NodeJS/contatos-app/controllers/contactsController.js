import Contact from '../models/Contact';

export const getContacts = async (req, res) => {
  try {
    const contacts = await Contact.find({});
    res.status(200).json({ success: true, data: contacts });
  } catch (error) {
    res.status(400).json({ success: false, error: error.message });
  }
};

export const createContact = async (req, res) => {
  try {
    const contact = await Contact.create(req.body);
    res.status(201).json({ success: true, data: contact });
  } catch (error) {
    res.status(400).json({ success: false, error: error.message });
  }
};

export const getContactById = async (req, res) => {
  const { id } = req.query;

  try {
    const contact = await Contact.findById(id);
    if (!contact) {
      return res.status(404).json({ success: false, message: 'Contact not found' });
    }
    res.status(200).json({ success: true, data: contact });
  } catch (error) {
    res.status(400).json({ success: false, error: error.message });
  }
};

export const updateContact = async (req, res) => {
  const { id } = req.query;

  try {
    const contact = await Contact.findByIdAndUpdate(id, req.body, {
      new: true,
      runValidators: true,
    });
    if (!contact) {
      return res.status(404).json({ success: false, message: 'Contact not found' });
    }
    res.status(200).json({ success: true, data: contact });
  } catch (error) {
    res.status(400).json({ success: false, error: error.message });
  }
};

export const deleteContact = async (req, res) => {
  const { id } = req.query;

  try {
    const deletedContact = await Contact.deleteOne({ _id: id });
    if (!deletedContact.deletedCount) {
      return res.status(404).json({ success: false, message: 'Contact not found' });
    }
    res.status(200).json({ success: true, data: {} });
  } catch (error) {
    res.status(400).json({ success: false, error: error.message });
  }
};
