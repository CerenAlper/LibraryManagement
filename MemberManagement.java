package domain.classes;

public class MemberManagement {
	private Member[] members;

	public Member[] getMembers() {
		return members;
	}

	public MemberManagement(Member[] members) {
		super();
		this.members = members;
	}
	
	public Member findMemberById(int id) {
		if (members[id - 1].getId() == id) {
			return members[id - 1];
		}
		
		for(Member member: members) {
			if (member.getId() == id)
				return member;
		}
		
		return null;
	}
}
