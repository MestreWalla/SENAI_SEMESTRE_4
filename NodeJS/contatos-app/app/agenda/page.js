import Head from 'next/head';
// import ContactCard from '../../components/ContactCard';
// import GroupList from '../../components/GroupList';
// import Header from '@/components/Header';
// import Sidebar from '../../components/Sidebar';
import useSWR from 'swr';

const fetcher = (url) => fetch(url).then((res) => res.json());

export default function Agenda() {
  const { data: contacts, error: contactsError } = useSWR('/api/contacts', fetcher);
  const { data: groups, error: groupsError } = useSWR('/api/groups', fetcher);

  if (contactsError || groupsError) return <div>Failed to load</div>;
  if (!contacts || !groups) return <div>Loading...</div>;

  return (
    <div>
      <Head>
        <title>Agenda</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
      </Head>
      <Header />
      <Sidebar groups={groups} />
      <main>
        {contacts.map((contact) => (
          <ContactCard key={contact._id} contact={contact} />
        ))}
      </main>
    </div>
  );
}
