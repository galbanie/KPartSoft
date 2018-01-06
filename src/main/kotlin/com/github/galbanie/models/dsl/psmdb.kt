package com.github.galbanie.models.dsl.psmdb

import com.github.galbanie.utils.ParameterType
import com.github.galbanie.utils.Priority
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction
import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormatter


/**
 * Created by Galbanie on 2017-10-09.
 */
object Sessions : Table("SESSIONS"){
    val id = integer("SESSION_ID").primaryKey().autoIncrement()
    val user_id = integer("USER_ID").references(Users.id, ReferenceOption.CASCADE)
    val isUserProcess = bool("IS_USER_PROCESS").default(false)
}

object HistoricSessions : Table("HISTORIC_SESSIONS"){
    val id = integer("HISTORIC_SESSION_ID").primaryKey().autoIncrement()
    val session_id = integer("SESSION_ID").references(Sessions.id, ReferenceOption.NO_ACTION)
    val dateLogin = date("DATE_LOGIN")
    val dateLogout = date("DATE_LOGOUT")
}

object Users : Table("USERS"){
    val id = integer("USER_ID").primaryKey().autoIncrement()
    val username = varchar("USERNAME", 255).uniqueIndex()
    val password = varchar("PASSWORD",255)
    val passwordSalt = varchar("PASSWORD_SALT",255).nullable()
    val firstName = varchar("FIRST_NAME",255).nullable()
    val lastName = varchar("LAST_NAME",255).nullable()
    val email = varchar("EMAIL",255).nullable()
    val accountActive = bool("ACCOUNT_ACTIVE").default(false)
    val dateExpiration = date("DATE_EXPIRATION").nullable()
    val dateCreation = date("DATE_CREATION").nullable()
    val dateModified = date("DATE_MODIFIED").nullable()
}

object Roles : Table("ROLES"){
    val id = integer("ROLE_ID").primaryKey().autoIncrement()
    val roleName = varchar("ROLE_NAME", 255).uniqueIndex()
    val dateCreation = date("DATE_CREATION").nullable()
    val dateModified = date("DATE_MODIFIED").nullable()
}

object UsersRoles : Table("USERS_ROLES"){
    val user_id = integer("USER_ID").references(Users.id, ReferenceOption.CASCADE)
    val role_id = integer("ROLE_ID").references(Roles.id, ReferenceOption.CASCADE)
}

object Actions : Table("ACTIONS"){
    val id = integer("ACTION_ID").primaryKey().autoIncrement()
    val actionName = varchar("ACTION_NAME", 255).uniqueIndex()
    val priority = enumeration("PRIORITY", Priority::class.java)
}

object Parameters : Table("PARAMETERS"){
    val id = integer("PARAMETER_ID").primaryKey().autoIncrement()
    val type = enumeration("TYPE", ParameterType::class.java)
    val field = varchar("FIELD",255).nullable()
    val value = varchar("VALUE",255).nullable()
}

object Entries : Table("Entries"){
    val input_id = integer("INPUT").references(ParameterActions.id, ReferenceOption.CASCADE)
    val constraintOutput_id = integer("CONSTRAINT_OUTPUTS").references(ParameterActions.id, ReferenceOption.CASCADE)
    //val constraintOutput_id = integer("CONSTRAINT_OUTPUT_ID").references(ConstraintOutputs.id, ReferenceOption.CASCADE)
    val valid = bool("VALID").default(false)
    val creator_id = integer("USER_ID").references(Users.id, ReferenceOption.SET_NULL)
    val reporter_id = integer("USER_ID").references(Users.id, ReferenceOption.SET_NULL)
    val dateCreation = date("DATE_CREATION").nullable()
    val dateModified = date("DATE_MODIFIED").nullable()
}

/*object ConstraintOutputs : Table("CONSTRAINT_OUTPUTS"){
    val id = integer("CONSTRAINT_OUTPUT_ID").primaryKey().autoIncrement()
    val output_id = integer("OUTPUT").references(ParameterActions.id, ReferenceOption.CASCADE)
    val constraint_id = integer("CONSTRAINT").references(ParameterActions.id, ReferenceOption.CASCADE)
}*/

object ParameterActions : Table("ACTIONS_PARAMETERS"){
    val id = integer("PARAMETER_ACTIONS_ID").primaryKey().autoIncrement()
    val parameter_id = integer("PARAMETER_ID").references(Parameters.id, ReferenceOption.CASCADE)
    val action_id = integer("ACTION_ID").references(Actions.id, ReferenceOption.CASCADE)
}

/*object Applications : Table("APPLICATIONS"){
    val applications_id = integer("APPLICATION_ID").primaryKey().autoIncrement()
    //val partToBaseVehicle_id = integer("PART_BASE_VEHICLE_ID").references(PartToBaseVehicles.id, ReferenceOption.SET_NULL)
    val vehicleType_id = integer("VEHICLE_TYPE_ID").references(VehicleTypes.id, ReferenceOption.SET_NULL)
    val model_id = integer("MODEL_ID").references(Models.id, ReferenceOption.SET_NULL)
    val subModel_id = integer("SUB_MODEL_ID").references(SubModels.id, ReferenceOption.SET_NULL)
    val mfrBodyCode_id = integer("MFR_BODY_CODE_ID").references(MfrBodyCodes.id, ReferenceOption.SET_NULL)
    val bodyStyleConfig_id = integer("BODY_STYLE_CONFIG_ID").references(BodyStyleConfigs.id, ReferenceOption.SET_NULL)
    val bodyNumDoors_id = integer("BODY_NUM_DOORS_ID").references(BodyNumDoors.id, ReferenceOption.SET_NULL)
    val bodyType_id = integer("BODY_TYPE_ID").references(BodyTypes.id, ReferenceOption.SET_NULL)
    val driveType_id = integer("DRIVE_TYPE_ID").references(DriveTypes.id, ReferenceOption.SET_NULL)
    val engineBase_id = integer("ENGINE_BASE_ID").references(EngineBases.id, ReferenceOption.SET_NULL)
    val engineDesignation_id = integer("ENGINE_DESIGNATION_ID").references(EngineDesignations.id, ReferenceOption.SET_NULL)
    val engineVIN_id = integer("ENGINE_VIN_ID").references(EngineVINs.id, ReferenceOption.SET_NULL)
    val engineMfr_id = integer("ENGINE_MFR_ID").references(EngineMfrs.id, ReferenceOption.SET_NULL)
    val fuelDeliveryConfig_id = integer("FUEL_DELIVERY_CONFIG_ID").references(FuelDeliveryConfigs.id, ReferenceOption.SET_NULL)
    val fuelDeliveryType_id = integer("FUEL_DELIVERY_TYPE_ID").references(FuelDeliveryTypes.id, ReferenceOption.SET_NULL)
    val fuelDeliverySubType_id = integer("FUEL_DELIVERY_SUBTYPE_ID").references(FuelDeliverySubTypes.id, ReferenceOption.SET_NULL)
    val fuelSysControlType_id = integer("FUEL_SYS_CONTROL_TYPE_ID").references(FuelSystemControlTypes.id, ReferenceOption.SET_NULL)
    val fuelSystemDesign_id = integer("FUEL_SYSTEM_DESIGN_ID").references(FuelSystemDesigns.id, ReferenceOption.SET_NULL)
    val aspiration_id = integer("ASPIRATION_ID").references(Aspirations.id, ReferenceOption.SET_NULL)
    val cylHeadType_id = integer("CYL_HEAD_TYPE_ID").references(CylinderHeadTypes.id, ReferenceOption.SET_NULL)
    val fuelType_id = integer("FUEL_TYPE_ID").references(FuelTypes.id, ReferenceOption.SET_NULL)
    val ignitionSystemType_id = integer("IGNITION_SYSTEM_TYPE_ID").references(IgnitionSystemTypes.id, ReferenceOption.SET_NULL)
    val transmissionType_id = integer("TRANSMISSION_TYPE_ID").references(TransmissionTypes.id, ReferenceOption.SET_NULL)
    val transmissionBase_id = integer("TRANSMISSION_BASE_ID").references(TransmissionBases.id, ReferenceOption.SET_NULL)
    val transmissionControlType_id = integer("TRANSMISSION_CONTROL_TYPE_ID").references(TransmissionControlTypes.id, ReferenceOption.SET_NULL)
    val transmissionMfrCode_id = integer("TRANSMISSION_MFR_CODE_ID").references(TransmissionMfrCodes.id, ReferenceOption.SET_NULL)
    val transmissionNumSpeeds_id = integer("TRANSMISSION_NUM_SPEEDS_ID").references(TransmissionNumSpeeds.id, ReferenceOption.SET_NULL)
    val bedLength_id = integer("BED_LENGTH_ID").references(BedLengths.id, ReferenceOption.SET_NULL)
    val bedType_id = integer("BED_TYPE_ID").references(BedTypes.id, ReferenceOption.SET_NULL)
    val bedConfig_id = integer("BED_CONFIG_ID").references(BedConfigs.id, ReferenceOption.SET_NULL)
    val wheelBase_id = integer("WHEEL_BASE_ID").references(WheelBases.id, ReferenceOption.SET_NULL)
    val frontBrakeType_id = integer("FRONT_BRAKE_TYPE_ID").references(BrakeTypes.id, ReferenceOption.SET_NULL)
    val rearBrakeType_id = integer("REAR_BRAKE_TYPE_ID").references(BrakeTypes.id, ReferenceOption.SET_NULL)
    val frontSpringType_id = integer("FRONT_SPRING_TYPE_ID").references(SpringTypes.id, ReferenceOption.SET_NULL)
    val brakeSystem_id = integer("BRAKE_SYSTEM_ID").references(BrakeSystems.id, ReferenceOption.SET_NULL)
    val brakeType_id = integer("BRAKE_TYPE_ID").references(BrakeTypes.id, ReferenceOption.SET_NULL)
    val brakeABS_id = integer("BRAKE_ABS_ID").references(BrakeAbs.id, ReferenceOption.SET_NULL)
    val rearSpringType_id = integer("REAR_SPRING_TYPE_ID").references(SpringTypes.id, ReferenceOption.SET_NULL)
    val steeringType_id = integer("STEERING_TYPE_ID").references(SteeringTypes.id, ReferenceOption.SET_NULL)
    val steeringSystem_id = integer("STEERING_SYSTEM_ID").references(SteeringSystems.id, ReferenceOption.SET_NULL)
    //val restraintType_id = integer("MODEL_ID")
    val region_id = integer("REGION_ID").references(Regions.id, ReferenceOption.SET_NULL)
    val engineVersion_id = integer("ENGINE_VERSION_ID").references(EngineVersions.id, ReferenceOption.SET_NULL)
    val engineValves_id = integer("ENGINE_VALVES_ID").references(EngineValves.id, ReferenceOption.SET_NULL)
    val note = varchar("NOTE",255)
    val dateAdded = date("DATE_ADDED")
    val dateModified = date("DATE_MODIFIED")
    val isValidable = bool("IS_VALIDABLE")
    val quantity = integer("QUANTITY")
    val upSizeTs = integer("UPSIZETS")
    val powerOutput_id = integer("POWER_OUTPUTS_ID").references(PowerOutputs.id, ReferenceOption.SET_NULL)
    val assetLogicalName = varchar("ASSET_LOGICAL_NAME",255)
    val assetItemRef = varchar("ASSET_ITEM_REF",255)
    val assetFileName = varchar("ASSET_FILE_NAME",255)
    val transmissionElecControlled_id = integer("TRANSMISSION_ELEC_CONTROLLED_ID").references(ElecControlleds.id, ReferenceOption.SET_NULL)
    val transmissionMFR_id = integer("TRANSMISSION_MFR_ID").references(TransmissionMfrs.id, ReferenceOption.SET_NULL)
}*/

object Applications : Table("APPLICATIONS"){
    val applications_id = integer("APPLICATION_ID").primaryKey().autoIncrement()
    //val partToBaseVehicle_id = integer("PART_BASE_VEHICLE_ID").references(PartToBaseVehicles.id, ReferenceOption.SET_NULL)
    val vehicleType_id = integer("VEHICLE_TYPE_ID")
    val model_id = integer("MODEL_ID")
    val subModel_id = integer("SUB_MODEL_ID")
    val mfrBodyCode_id = integer("MFR_BODY_CODE_ID")
    val bodyStyleConfig_id = integer("BODY_STYLE_CONFIG_ID")
    val bodyNumDoors_id = integer("BODY_NUM_DOORS_ID")
    val bodyType_id = integer("BODY_TYPE_ID")
    val driveType_id = integer("DRIVE_TYPE_ID")
    val engineBase_id = integer("ENGINE_BASE_ID")
    val engineDesignation_id = integer("ENGINE_DESIGNATION_ID")
    val engineVIN_id = integer("ENGINE_VIN_ID")
    val engineMfr_id = integer("ENGINE_MFR_ID")
    val fuelDeliveryConfig_id = integer("FUEL_DELIVERY_CONFIG_ID")
    val fuelDeliveryType_id = integer("FUEL_DELIVERY_TYPE_ID")
    val fuelDeliverySubType_id = integer("FUEL_DELIVERY_SUBTYPE_ID")
    val fuelSysControlType_id = integer("FUEL_SYS_CONTROL_TYPE_ID")
    val fuelSystemDesign_id = integer("FUEL_SYSTEM_DESIGN_ID")
    val aspiration_id = integer("ASPIRATION_ID")
    val cylHeadType_id = integer("CYL_HEAD_TYPE_ID")
    val fuelType_id = integer("FUEL_TYPE_ID")
    val ignitionSystemType_id = integer("IGNITION_SYSTEM_TYPE_ID")
    val transmissionType_id = integer("TRANSMISSION_TYPE_ID")
    val transmissionBase_id = integer("TRANSMISSION_BASE_ID")
    val transmissionControlType_id = integer("TRANSMISSION_CONTROL_TYPE_ID")
    val transmissionMfrCode_id = integer("TRANSMISSION_MFR_CODE_ID")
    val transmissionNumSpeeds_id = integer("TRANSMISSION_NUM_SPEEDS_ID")
    val bedLength_id = integer("BED_LENGTH_ID")
    val bedType_id = integer("BED_TYPE_ID")
    val bedConfig_id = integer("BED_CONFIG_ID")
    val wheelBase_id = integer("WHEEL_BASE_ID")
    val frontBrakeType_id = integer("FRONT_BRAKE_TYPE_ID")
    val rearBrakeType_id = integer("REAR_BRAKE_TYPE_ID")
    val frontSpringType_id = integer("FRONT_SPRING_TYPE_ID")
    val brakeSystem_id = integer("BRAKE_SYSTEM_ID")
    val brakeType_id = integer("BRAKE_TYPE_ID")
    val brakeABS_id = integer("BRAKE_ABS_ID")
    val rearSpringType_id = integer("REAR_SPRING_TYPE_ID")
    val steeringType_id = integer("STEERING_TYPE_ID")
    val steeringSystem_id = integer("STEERING_SYSTEM_ID")
    //val restraintType_id = integer("MODEL_ID")
    val region_id = integer("REGION_ID")
    val engineVersion_id = integer("ENGINE_VERSION_ID")
    val engineValves_id = integer("ENGINE_VALVES_ID")
    val note = text("NOTE")
    val dateAdded = date("DATE_ADDED")
    val dateModified = date("DATE_MODIFIED")
    val isValidable = bool("IS_VALIDABLE")
    val quantity = integer("QUANTITY")
    val upSizeTs = integer("UPSIZETS")
    val powerOutput_id = integer("POWER_OUTPUTS_ID")
    val assetLogicalName = varchar("ASSET_LOGICAL_NAME",255)
    val assetItemRef = varchar("ASSET_ITEM_REF",255)
    val assetFileName = varchar("ASSET_FILE_NAME",255)
    val transmissionElecControlled_id = integer("TRANSMISSION_ELEC_CONTROLLED_ID")
    val transmissionMFR_id = integer("TRANSMISSION_MFR_ID")
}

object PartToBaseVehicles : Table("PART_TO_BASE_VEHICLES"){
    val id = integer("PART_TO_BASE_VEHICLE_ID").primaryKey()
    //val baseVehicle_id = integer("BASE_VEHICLE_ID").references(BaseVehicles.id, ReferenceOption.CASCADE)
    //val part_id = integer("PART_ID").references(Parts.id, ReferenceOption.CASCADE)
    //val position_id = integer("POSITION_ID").references(Positions.id, ReferenceOption.CASCADE)
}

fun main(args: Array<String>) {
    Database.connect("jdbc:mysql://localhost:3306/psmdb", driver = "com.mysql.jdbc.Driver",user = "root",password = "carole225")
    transaction {
        SchemaUtils.create(Users,Roles,UsersRoles,Actions,Parameters,ParameterActions,Sessions,HistoricSessions,Applications)

        Users.insert {
            it[username] = "galbanie"
            it[password] = "mypass"
            it[passwordSalt] = "mypass"
            it[firstName] = "Galbanie"
            it[lastName] = "Galbanie"
            it[email] = "galbanie@gsi.com"
            it[accountActive] = true
            it[dateCreation] = DateTime.now()
            it[dateModified] = DateTime.now()
            //it[dateExpiration] = DateTime.parse("2018/12/01", DateTimeFormatter("yyyy/mm/dd"))
        }

        Users.selectAll().forEach {
            println("${it[Users.id]} - ${it[Users.username]} - ${it[Users.email]}")
        }
    }
}