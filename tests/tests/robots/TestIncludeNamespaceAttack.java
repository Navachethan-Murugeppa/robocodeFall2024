/*******************************************************************************
 * Copyright (c) 2001, 2008 Mathew A. Nelson and Robocode contributors
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://robocode.sourceforge.net/license/cpl-v10.html
 *
 * Contributors:
 *     Pavel Savara
 *     - Initial implementation
 *******************************************************************************/
package robots;


import helpers.RobotTestBed;
import helpers.Assert;
import org.junit.Test;
import org.junit.After;
import robocode.battle.events.TurnEndedEvent;


/**
 * @author Pavel Savara (original)
 */
public class TestIncludeNamespaceAttack extends RobotTestBed {
	boolean messaged;
	boolean messagedBreakthru;

	@Test
	public void run() {
		super.run();
	}

	public void onTurnEnded(TurnEndedEvent event) {
		super.onTurnEnded(event);
		final String out = event.getTurnSnapshot().getRobots().get(1).getOutputStreamSnapshot();

		if (out.contains(
				"java.security.AccessControlException: Preventing Robot Loader from access to the internal Robocode pakage: robocode.manager")) {
			messaged = true;
		}
		if (out.contains("Hacked!!!")) {
			messagedBreakthru = true;
		}
	}

	@Override
	public String getRobotNames() {
		return "sample.SittingDuck,testing.IncludeNamespaceAttack";
	}

	@After
	public void tearDownAttack() {
		Assert.assertFalse(messagedBreakthru);
		Assert.assertTrue(messaged);
	}

}