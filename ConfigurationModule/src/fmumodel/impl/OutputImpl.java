/**
 */
package fmumodel.impl;

import fmumodel.FmumodelPackage;
import fmumodel.Output;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Output</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link fmumodel.impl.OutputImpl#getName <em>Name</em>}</li>
 *   <li>{@link fmumodel.impl.OutputImpl#getValue <em>Value</em>}</li>
 * </ul>
 *
 * @generated
 */
public class OutputImpl extends MinimalEObjectImpl.Container implements Output {
   /**
    * The default value of the '{@link #getName() <em>Name</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getName()
    * @generated
    * @ordered
    */
   protected static final String NAME_EDEFAULT = null;

   /**
    * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getName()
    * @generated
    * @ordered
    */
   protected String name = NAME_EDEFAULT;

   /**
    * The default value of the '{@link #getValue() <em>Value</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getValue()
    * @generated
    * @ordered
    */
   protected static final double VALUE_EDEFAULT = 0.0;

   /**
    * The cached value of the '{@link #getValue() <em>Value</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getValue()
    * @generated
    * @ordered
    */
   protected double value = VALUE_EDEFAULT;

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   protected OutputImpl() {
      super();
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   @Override
   protected EClass eStaticClass() {
      return FmumodelPackage.Literals.OUTPUT;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   @Override
   public String getName() {
      return name;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   @Override
   public void setName(String newName) {
      String oldName = name;
      name = newName;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, FmumodelPackage.OUTPUT__NAME, oldName, name));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   @Override
   public double getValue() {
      return value;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   @Override
   public void setValue(double newValue) {
      double oldValue = value;
      value = newValue;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, FmumodelPackage.OUTPUT__VALUE, oldValue, value));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   @Override
   public Object eGet(int featureID, boolean resolve, boolean coreType) {
      switch (featureID) {
         case FmumodelPackage.OUTPUT__NAME:
            return getName();
         case FmumodelPackage.OUTPUT__VALUE:
            return getValue();
      }
      return super.eGet(featureID, resolve, coreType);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   @Override
   public void eSet(int featureID, Object newValue) {
      switch (featureID) {
         case FmumodelPackage.OUTPUT__NAME:
            setName((String)newValue);
            return;
         case FmumodelPackage.OUTPUT__VALUE:
            setValue((Double)newValue);
            return;
      }
      super.eSet(featureID, newValue);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   @Override
   public void eUnset(int featureID) {
      switch (featureID) {
         case FmumodelPackage.OUTPUT__NAME:
            setName(NAME_EDEFAULT);
            return;
         case FmumodelPackage.OUTPUT__VALUE:
            setValue(VALUE_EDEFAULT);
            return;
      }
      super.eUnset(featureID);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   @Override
   public boolean eIsSet(int featureID) {
      switch (featureID) {
         case FmumodelPackage.OUTPUT__NAME:
            return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
         case FmumodelPackage.OUTPUT__VALUE:
            return value != VALUE_EDEFAULT;
      }
      return super.eIsSet(featureID);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   @Override
   public String toString() {
      if (eIsProxy()) return super.toString();

      StringBuilder result = new StringBuilder(super.toString());
      result.append(" (name: ");
      result.append(name);
      result.append(", value: ");
      result.append(value);
      result.append(')');
      return result.toString();
   }

} //OutputImpl
