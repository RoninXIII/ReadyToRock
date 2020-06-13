package readyToRock;


import org.kie.api.KieServices;
import org.kie.api.event.rule.ObjectDeletedEvent;
import org.kie.api.event.rule.ObjectInsertedEvent;
import org.kie.api.event.rule.ObjectUpdatedEvent;
import org.kie.api.event.rule.RuleRuntimeEventListener;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.FactHandle;

public class rockTest {

	public static void main(String[] args) {
		
		KieServices ks = KieServices.Factory.get();
		KieContainer kc = ks.getKieClasspathContainer();
		KieSession wm = kc.newKieSession("ksession-rules");
		
		
		
		wm.addEventListener(new RuleRuntimeEventListener() {
			
			@Override
			public void objectUpdated(ObjectUpdatedEvent arg0) {

				System.out.println("Object updated!"+ arg0.getObject().toString());
				
			}
			
			@Override
			public void objectInserted(ObjectInsertedEvent arg0) {
				
				System.out.println("Object inserted!"+ arg0.getObject().toString());
				
			}
			
			@Override
			public void objectDeleted(ObjectDeletedEvent arg0) {
				
				System.out.println("Object deleted!"+ arg0.getOldObject().toString());
				
			}
		} );
		
		
		
		Player player1 = new Player();
		player1.setColor("Red");
	
		Player player2 = new Player();
		player2.setColor("Blue");
		
		wm.insert(player1);
		wm.insert(player2);
	/*	FactHandle handleOfPlayer1 = wm.insert(player1);
		FactHandle handleOfPlayer2 = wm.insert(player2);
		player1.setActions(0);
		wm.update(handleOfPlayer1, player1);*/
		wm.fireAllRules();
		
		Board board = new Board(10,10);
		board.toString();
	}
	

}
