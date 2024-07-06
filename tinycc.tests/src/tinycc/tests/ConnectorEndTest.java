/**
 */
package tinycc.tests;

import junit.textui.TestRunner;

import tinycc.ConnectorEnd;
import tinycc.TinyccFactory;

/**
 * <!-- begin-user-doc -->
 * A test case for the model object '<em><b>Connector End</b></em>'.
 * <!-- end-user-doc -->
 * @generated
 */
public class ConnectorEndTest extends SimlementTest {

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(ConnectorEndTest.class);
	}

	/**
	 * Constructs a new Connector End test case with the given name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ConnectorEndTest(String name) {
		super(name);
	}

	/**
	 * Returns the fixture for this Connector End test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected ConnectorEnd getFixture() {
		return (ConnectorEnd)fixture;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see junit.framework.TestCase#setUp()
	 * @generated
	 */
	@Override
	protected void setUp() throws Exception {
		setFixture(TinyccFactory.eINSTANCE.createConnectorEnd());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see junit.framework.TestCase#tearDown()
	 * @generated
	 */
	@Override
	protected void tearDown() throws Exception {
		setFixture(null);
	}

} //ConnectorEndTest
