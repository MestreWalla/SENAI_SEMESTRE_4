import Head from 'next/head';
import ContactForm from '../../components/ContactForm';
import { useRouter } from 'next/router';

const CreateContact = () => {
  const router = useRouter();

  const handleSubmit = async (contactData) => {
    try {
      const res = await fetch('/api/contacts', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(contactData),
      });

      if (res.ok) {
        router.push('/agenda'); // Redireciona para a página de agenda após o cadastro
      } else {
        console.error('Failed to create contact');
      }
    } catch (error) {
      console.error('An error occurred while creating the contact:', error);
    }
  };

  return (
    <div>
      <Head>
        <title>Create Contact</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
      </Head>
      <h1>Create Contact</h1>
      <ContactForm onSubmit={handleSubmit} />
    </div>
  );
};

export default CreateContact;
