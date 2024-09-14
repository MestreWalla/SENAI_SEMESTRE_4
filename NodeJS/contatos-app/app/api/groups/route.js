import { getGroups, createGroup } from "@/controllers/groupsController";

export async function GET(req) {
  try {
    const response = await getGroups(req);
    return response;
  } catch (error) {
    return new Response(
      JSON.stringify({ success: false, error: error.message }),
      { status: 500 }
    );
  }
}

export async function POST(req) {
  try {
    const response = await createGroup(req);
    return response;
  } catch (error) {
    return new Response(
      JSON.stringify({ success: false, error: error.message }),
      { status: 500 }
    );
  }
}
