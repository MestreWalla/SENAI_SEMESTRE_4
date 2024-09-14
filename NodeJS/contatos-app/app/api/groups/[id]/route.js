import { getGroupById, updateGroup, deleteGroup } from "@/controllers/groupsController";

export async function GET(req) {
  try {
    const response = await getGroupById(req);
    return response;
  } catch (error) {
    return new Response(
      JSON.stringify({ success: false, error: error.message }),
      { status: 500 }
    );
  }
}

export async function PUT(req) {
  try {
    const response = await updateGroup(req);
    return response;
  } catch (error) {
    return new Response(
      JSON.stringify({ success: false, error: error.message }),
      { status: 500 }
    );
  }
}

export async function DELETE(req) {
  try {
    const response = await deleteGroup(req);
    return response;
  } catch (error) {
    return new Response(
      JSON.stringify({ success: false, error: error.message }),
      { status: 500 }
    );
  }
}
