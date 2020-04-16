package com.valeriotor.beyondtheveil.entities.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * surgeon - valeriotor
 * Created using Tabula 7.0.0
 */
public class ModelSurgeon extends ModelBase {
    public ModelRenderer LowerBody1;
    public ModelRenderer LowerBody2;
    public ModelRenderer RightFrontUpperLeg;
    public ModelRenderer RightMidUpperLeg;
    public ModelRenderer LowerBody;
    public ModelRenderer RightBackUpperLeg;
    public ModelRenderer LeftFrontUpperLeg;
    public ModelRenderer LeftMidUpperLeg;
    public ModelRenderer LeftBackUpperLeg;
    public ModelRenderer LowerBody3;
    public ModelRenderer RightFrontLowerLeg;
    public ModelRenderer RightMidLowerLeg;
    public ModelRenderer UpperBody;
    public ModelRenderer Neck;
    public ModelRenderer RightUpperArm;
    public ModelRenderer LeftUpperArm;
    public ModelRenderer MainHead;
    public ModelRenderer FrontHead1;
    public ModelRenderer BackHead1;
    public ModelRenderer Eye1;
    public ModelRenderer Eye2;
    public ModelRenderer Eye3;
    public ModelRenderer Eye4;
    public ModelRenderer Eye5;
    public ModelRenderer Eye6;
    public ModelRenderer Eye7;
    public ModelRenderer Eye10;
    public ModelRenderer Eye11;
    public ModelRenderer Eye12;
    public ModelRenderer Eye14;
    public ModelRenderer Eye15;
    public ModelRenderer Eye16;
    public ModelRenderer Eye17;
    public ModelRenderer FrontHead2;
    public ModelRenderer Eye8;
    public ModelRenderer Eye9;
    public ModelRenderer Eye13;
    public ModelRenderer Eye18;
    public ModelRenderer BackHead2;
    public ModelRenderer BackHead3;
    public ModelRenderer RightLowerArm;
    public ModelRenderer RightUpperTentacle1;
    public ModelRenderer RightUpperTentacle2;
    public ModelRenderer RightUpperTentacle3;
    public ModelRenderer RightLowerTentacle1;
    public ModelRenderer RightLowerTentacle2;
    public ModelRenderer RightLowerTentacle3;
    public ModelRenderer LeftLowerArm;
    public ModelRenderer LeftUpperTentacle1;
    public ModelRenderer LeftUpperTentacle2;
    public ModelRenderer LeftUpperTentacle3;
    public ModelRenderer LeftLowerTentacle1;
    public ModelRenderer LeftLowerTentacle2;
    public ModelRenderer LeftLowerTentacle3;
    public ModelRenderer RightBackLowerLeg;
    public ModelRenderer LeftFrontLowerLeg;
    public ModelRenderer LeftMidLowerLeg;
    public ModelRenderer LeftBackLowerLeg;

    public ModelSurgeon() {
        this.textureWidth = 128;
        this.textureHeight = 64;
        this.UpperBody = new ModelRenderer(this, 56, 0);
        this.UpperBody.setRotationPoint(0.0F, -8.0F, -3.0F);
        this.UpperBody.addBox(-5.0F, 0.0F, 0.0F, 10, 12, 10, 0.0F);
        this.setRotateAngle(UpperBody, 0.18203784098300857F, 0.0F, 0.0F);
        this.LeftUpperTentacle1 = new ModelRenderer(this, 0, 0);
        this.LeftUpperTentacle1.setRotationPoint(0.0F, 17.3F, 2.3F);
        this.LeftUpperTentacle1.addBox(-0.5F, 0.0F, 0.0F, 1, 10, 1, 0.0F);
        this.setRotateAngle(LeftUpperTentacle1, 1.4570008595648662F, 0.5462880558742251F, -0.091106186954104F);
        this.Eye7 = new ModelRenderer(this, 117, 61);
        this.Eye7.setRotationPoint(5.0F, 5.0F, 7.0F);
        this.Eye7.addBox(-0.5F, 0.0F, 0.0F, 1, 1, 1, 0.0F);
        this.Eye11 = new ModelRenderer(this, 117, 61);
        this.Eye11.setRotationPoint(-5.0F, 3.7F, 4.6F);
        this.Eye11.addBox(-0.5F, 0.0F, 0.0F, 1, 1, 1, 0.0F);
        this.LeftBackLowerLeg = new ModelRenderer(this, 0, 25);
        this.LeftBackLowerLeg.setRotationPoint(0.0F, 9.1F, 0.1F);
        this.LeftBackLowerLeg.addBox(-1.5F, 0.0F, 0.0F, 3, 13, 3, 0.0F);
        this.setRotateAngle(LeftBackLowerLeg, 0.0F, 0.0F, 0.7285004297824331F);
        this.BackHead3 = new ModelRenderer(this, 56, 23);
        this.BackHead3.setRotationPoint(0.0F, 0.5F, 2.7F);
        this.BackHead3.addBox(-3.0F, 0.0F, 0.0F, 6, 6, 3, 0.0F);
        this.setRotateAngle(BackHead3, -0.27314402793711257F, 0.0F, 0.0F);
        this.RightBackLowerLeg = new ModelRenderer(this, 0, 25);
        this.RightBackLowerLeg.setRotationPoint(0.0F, 9.1F, 0.1F);
        this.RightBackLowerLeg.addBox(-1.5F, 0.0F, 0.0F, 3, 13, 3, 0.0F);
        this.setRotateAngle(RightBackLowerLeg, 0.0F, 0.0F, -0.7285004297824331F);
        this.BackHead1 = new ModelRenderer(this, 12, 22);
        this.BackHead1.setRotationPoint(0.0F, 0.5F, 9.3F);
        this.BackHead1.addBox(-4.5F, 0.0F, 0.0F, 9, 9, 3, 0.0F);
        this.setRotateAngle(BackHead1, -0.091106186954104F, 0.0F, 0.0F);
        this.Neck = new ModelRenderer(this, 33, 0);
        this.Neck.setRotationPoint(0.0F, -0.9F, 0.9F);
        this.Neck.addBox(-3.0F, 0.0F, 0.0F, 6, 2, 6, 0.0F);
        this.setRotateAngle(Neck, 0.136659280431156F, 0.0F, 0.0F);
        this.LeftMidLowerLeg = new ModelRenderer(this, 40, 36);
        this.LeftMidLowerLeg.setRotationPoint(0.0F, 9.1F, 0.1F);
        this.LeftMidLowerLeg.addBox(-1.5F, 0.0F, 0.0F, 3, 15, 3, 0.0F);
        this.setRotateAngle(LeftMidLowerLeg, 0.0F, 0.0F, 0.7285004297824331F);
        this.LeftLowerTentacle3 = new ModelRenderer(this, 0, 0);
        this.LeftLowerTentacle3.setRotationPoint(0.0F, 9.3F, 0.0F);
        this.LeftLowerTentacle3.addBox(-0.5F, 0.0F, 0.0F, 1, 10, 1, 0.0F);
        this.setRotateAngle(LeftLowerTentacle3, -0.22759093446006054F, 0.0F, 0.0F);
        this.Eye1 = new ModelRenderer(this, 117, 61);
        this.Eye1.setRotationPoint(-5.0F, 3.0F, 2.0F);
        this.Eye1.addBox(-0.5F, 0.0F, 0.0F, 1, 1, 1, 0.0F);
        this.RightUpperTentacle3 = new ModelRenderer(this, 0, 0);
        this.RightUpperTentacle3.setRotationPoint(0.0F, 17.0F, 2.0F);
        this.RightUpperTentacle3.addBox(-0.5F, 0.0F, 0.0F, 1, 10, 1, 0.0F);
        this.setRotateAngle(RightUpperTentacle3, 2.367539130330308F, 0.0F, 0.0F);
        this.Eye18 = new ModelRenderer(this, 117, 61);
        this.Eye18.setRotationPoint(-0.9F, 4.4F, -1.0F);
        this.Eye18.addBox(-0.5F, 0.0F, 0.0F, 1, 1, 1, 0.0F);
        this.RightLowerTentacle1 = new ModelRenderer(this, 0, 0);
        this.RightLowerTentacle1.setRotationPoint(0.2F, 9.6F, 0.0F);
        this.RightLowerTentacle1.addBox(-0.5F, 0.0F, 0.0F, 1, 10, 1, 0.0F);
        this.setRotateAngle(RightLowerTentacle1, 0.8651597102135892F, -0.22759093446006054F, 0.0F);
        this.Eye14 = new ModelRenderer(this, 117, 61);
        this.Eye14.setRotationPoint(-2.0F, -1.0F, 3.0F);
        this.Eye14.addBox(-0.5F, 0.0F, 0.0F, 1, 1, 1, 0.0F);
        this.RightLowerTentacle2 = new ModelRenderer(this, 0, 0);
        this.RightLowerTentacle2.setRotationPoint(-0.2F, 9.3F, -0.1F);
        this.RightLowerTentacle2.addBox(-0.5F, 0.0F, 0.0F, 1, 10, 1, 0.0F);
        this.setRotateAngle(RightLowerTentacle2, 0.4553564018453205F, 0.0F, -0.4553564018453205F);
        this.MainHead = new ModelRenderer(this, 0, 2);
        this.MainHead.setRotationPoint(0.0F, -10.0F, -2.0F);
        this.MainHead.addBox(-5.0F, 0.0F, 0.0F, 10, 10, 10, 0.0F);
        this.Eye5 = new ModelRenderer(this, 120, 59);
        this.Eye5.setRotationPoint(5.0F, 2.2F, 3.9F);
        this.Eye5.addBox(-0.5F, 0.0F, 0.0F, 1, 2, 2, 0.0F);
        this.Eye17 = new ModelRenderer(this, 117, 61);
        this.Eye17.setRotationPoint(-0.7F, -1.0F, 5.9F);
        this.Eye17.addBox(-0.5F, 0.0F, 0.0F, 1, 1, 1, 0.0F);
        this.Eye3 = new ModelRenderer(this, 117, 61);
        this.Eye3.setRotationPoint(-5.0F, 2.0F, 7.0F);
        this.Eye3.addBox(-0.5F, 0.0F, 0.0F, 1, 1, 1, 0.0F);
        this.FrontHead2 = new ModelRenderer(this, 72, 33);
        this.FrontHead2.setRotationPoint(0.0F, 1.0F, -1.0F);
        this.FrontHead2.addBox(-3.5F, 0.0F, 0.0F, 7, 7, 1, 0.0F);
        this.BackHead2 = new ModelRenderer(this, 36, 22);
        this.BackHead2.setRotationPoint(0.0F, 1.0F, 2.3F);
        this.BackHead2.addBox(-3.5F, 0.0F, 0.0F, 7, 7, 3, 0.0F);
        this.setRotateAngle(BackHead2, -0.18203784098300857F, 0.0F, 0.0F);
        this.LeftUpperTentacle2 = new ModelRenderer(this, 0, 0);
        this.LeftUpperTentacle2.setRotationPoint(0.0F, 17.3F, 1.7F);
        this.LeftUpperTentacle2.addBox(-0.5F, 0.0F, 0.0F, 1, 10, 1, 0.0F);
        this.setRotateAngle(LeftUpperTentacle2, 1.7756979809790308F, -0.6373942428283291F, 0.0F);
        this.RightLowerTentacle3 = new ModelRenderer(this, 0, 0);
        this.RightLowerTentacle3.setRotationPoint(0.0F, 9.3F, 0.0F);
        this.RightLowerTentacle3.addBox(-0.5F, 0.0F, 0.0F, 1, 10, 1, 0.0F);
        this.setRotateAngle(RightLowerTentacle3, -0.22759093446006054F, 0.0F, 0.0F);
        this.LeftLowerArm = new ModelRenderer(this, 116, 21);
        this.LeftLowerArm.setRotationPoint(0.0F, 13.6F, 3.1F);
        this.LeftLowerArm.addBox(-1.5F, 0.0F, 0.0F, 3, 18, 3, 0.0F);
        this.setRotateAngle(LeftLowerArm, -2.4586453172844123F, 0.0F, 0.5462880558742251F);
        this.Eye13 = new ModelRenderer(this, 117, 61);
        this.Eye13.setRotationPoint(2.0F, 1.0F, -1.0F);
        this.Eye13.addBox(-0.5F, 0.0F, 0.0F, 1, 1, 1, 0.0F);
        this.LowerBody3 = new ModelRenderer(this, 88, 41);
        this.LowerBody3.setRotationPoint(0.0F, 1.0F, 10.0F);
        this.LowerBody3.addBox(-3.0F, 0.0F, 0.0F, 6, 6, 9, 0.0F);
        this.setRotateAngle(LowerBody3, -0.27314402793711257F, 0.0F, 0.0F);
        this.RightFrontLowerLeg = new ModelRenderer(this, 40, 36);
        this.RightFrontLowerLeg.setRotationPoint(0.0F, 9.1F, 0.0F);
        this.RightFrontLowerLeg.addBox(-1.5F, 0.0F, 0.0F, 3, 15, 3, 0.0F);
        this.setRotateAngle(RightFrontLowerLeg, 0.091106186954104F, 0.0F, -0.7285004297824331F);
        this.LeftFrontUpperLeg = new ModelRenderer(this, 0, 41);
        this.LeftFrontUpperLeg.setRotationPoint(4.0F, 3.0F, 0.0F);
        this.LeftFrontUpperLeg.addBox(-1.5F, 0.0F, 0.0F, 3, 10, 3, 0.0F);
        this.setRotateAngle(LeftFrontUpperLeg, 0.0F, 0.0F, -0.767944870877505F);
        this.LeftUpperArm = new ModelRenderer(this, 104, 21);
        this.LeftUpperArm.setRotationPoint(3.7F, 1.7F, 2.3F);
        this.LeftUpperArm.addBox(-1.5F, 0.0F, 0.0F, 3, 15, 3, 0.0F);
        this.setRotateAngle(LeftUpperArm, 0.27314402793711257F, 0.0F, -0.5462880558742251F);
        this.FrontHead1 = new ModelRenderer(this, 52, 33);
        this.FrontHead1.setRotationPoint(0.0F, 0.5F, -1.0F);
        this.FrontHead1.addBox(-4.5F, 0.0F, 0.0F, 9, 9, 1, 0.0F);
        this.LowerBody2 = new ModelRenderer(this, 60, 44);
        this.LowerBody2.setRotationPoint(0.0F, 0.6F, 18.9F);
        this.LowerBody2.addBox(-4.0F, 0.0F, 0.0F, 8, 8, 12, 0.0F);
        this.setRotateAngle(LowerBody2, -0.36425021489121656F, 0.0F, 0.0F);
        this.RightUpperTentacle1 = new ModelRenderer(this, 0, 0);
        this.RightUpperTentacle1.setRotationPoint(0.0F, 17.3F, 2.3F);
        this.RightUpperTentacle1.addBox(-0.5F, 0.0F, 0.0F, 1, 10, 1, 0.0F);
        this.setRotateAngle(RightUpperTentacle1, 1.4570008595648662F, 0.5462880558742251F, 0.091106186954104F);
        this.RightBackUpperLeg = new ModelRenderer(this, 0, 41);
        this.RightBackUpperLeg.setRotationPoint(-4.0F, 3.0F, 16.0F);
        this.RightBackUpperLeg.addBox(-1.5F, 0.0F, 0.0F, 3, 10, 3, 0.0F);
        this.setRotateAngle(RightBackUpperLeg, 0.22759093446006054F, 0.0F, 0.7740535232594852F);
        this.RightFrontUpperLeg = new ModelRenderer(this, 0, 41);
        this.RightFrontUpperLeg.setRotationPoint(-4.0F, 3.0F, 0.0F);
        this.RightFrontUpperLeg.addBox(-1.5F, 0.0F, 0.0F, 3, 10, 3, 0.0F);
        this.setRotateAngle(RightFrontUpperLeg, 0.0F, 0.0F, 0.7740535232594852F);
        this.Eye16 = new ModelRenderer(this, 117, 59);
        this.Eye16.setRotationPoint(1.4F, -1.0F, 1.3F);
        this.Eye16.addBox(-0.5F, 0.0F, 0.0F, 2, 1, 2, 0.0F);
        this.Eye15 = new ModelRenderer(this, 117, 61);
        this.Eye15.setRotationPoint(2.0F, -1.0F, 6.0F);
        this.Eye15.addBox(-0.5F, 0.0F, 0.0F, 1, 1, 1, 0.0F);
        this.LeftFrontLowerLeg = new ModelRenderer(this, 40, 36);
        this.LeftFrontLowerLeg.setRotationPoint(0.0F, 9.1F, 0.0F);
        this.LeftFrontLowerLeg.addBox(-1.5F, 0.0F, 0.0F, 3, 15, 3, 0.0F);
        this.setRotateAngle(LeftFrontLowerLeg, 0.10471975511965977F, 0.0F, 0.7285004297824331F);
        this.RightMidUpperLeg = new ModelRenderer(this, 0, 41);
        this.RightMidUpperLeg.setRotationPoint(-4.0F, 3.0F, 6.6F);
        this.RightMidUpperLeg.addBox(-1.5F, 0.0F, 0.0F, 3, 10, 3, 0.0F);
        this.setRotateAngle(RightMidUpperLeg, 0.22759093446006054F, 0.0F, 0.7740535232594852F);
        this.RightLowerArm = new ModelRenderer(this, 116, 21);
        this.RightLowerArm.setRotationPoint(0.0F, 13.6F, 3.1F);
        this.RightLowerArm.addBox(-1.5F, 0.0F, 0.0F, 3, 18, 3, 0.0F);
        this.setRotateAngle(RightLowerArm, -2.4586453172844123F, 0.0F, -0.5462880558742251F);
        this.Eye10 = new ModelRenderer(this, 117, 59);
        this.Eye10.setRotationPoint(5.0F, 2.0F, 8.0F);
        this.Eye10.addBox(-0.5F, 0.0F, 0.0F, 1, 1, 1, 0.0F);
        this.LeftMidUpperLeg = new ModelRenderer(this, 0, 41);
        this.LeftMidUpperLeg.setRotationPoint(4.0F, 3.0F, 6.6F);
        this.LeftMidUpperLeg.addBox(-1.5F, 0.0F, 0.0F, 3, 10, 3, 0.0F);
        this.setRotateAngle(LeftMidUpperLeg, 0.22689280275926282F, 0.0F, -0.7740535232594852F);
        this.LowerBody1 = new ModelRenderer(this, 0, 34);
        this.LowerBody1.setRotationPoint(0.0F, 0.0F, -6.0F);
        this.LowerBody1.addBox(-5.0F, 0.0F, 0.0F, 10, 10, 20, 0.0F);
        this.setRotateAngle(LowerBody1, -0.136659280431156F, 0.0F, 0.0F);
        this.LeftUpperTentacle3 = new ModelRenderer(this, 0, 0);
        this.LeftUpperTentacle3.setRotationPoint(0.0F, 17.0F, 2.0F);
        this.LeftUpperTentacle3.addBox(-0.5F, 0.0F, 0.0F, 1, 10, 1, 0.0F);
        this.setRotateAngle(LeftUpperTentacle3, 2.367539130330308F, 0.0F, 0.0F);
        this.RightMidLowerLeg = new ModelRenderer(this, 40, 36);
        this.RightMidLowerLeg.setRotationPoint(0.0F, 9.1F, 0.1F);
        this.RightMidLowerLeg.addBox(-1.5F, 0.0F, 0.0F, 3, 15, 3, 0.0F);
        this.setRotateAngle(RightMidLowerLeg, 0.0F, 0.0F, -0.7285004297824331F);
        this.Eye4 = new ModelRenderer(this, 117, 61);
        this.Eye4.setRotationPoint(5.0F, 5.0F, 1.0F);
        this.Eye4.addBox(-0.5F, 0.0F, 0.0F, 1, 1, 1, 0.0F);
        this.Eye6 = new ModelRenderer(this, 117, 61);
        this.Eye6.setRotationPoint(5.0F, 7.0F, 5.0F);
        this.Eye6.addBox(-0.5F, 0.0F, 0.0F, 1, 1, 1, 0.0F);
        this.LeftBackUpperLeg = new ModelRenderer(this, 0, 41);
        this.LeftBackUpperLeg.setRotationPoint(4.0F, 3.0F, 16.0F);
        this.LeftBackUpperLeg.addBox(-1.5F, 0.0F, 0.0F, 3, 10, 3, 0.0F);
        this.setRotateAngle(LeftBackUpperLeg, 0.22759093446006054F, 0.0F, -0.7740535232594852F);
        this.Eye9 = new ModelRenderer(this, 117, 61);
        this.Eye9.setRotationPoint(2.9F, 5.0F, -1.0F);
        this.Eye9.addBox(-0.5F, 0.0F, 0.0F, 1, 1, 1, 0.0F);
        this.LeftLowerTentacle2 = new ModelRenderer(this, 0, 0);
        this.LeftLowerTentacle2.setRotationPoint(-0.2F, 9.3F, -0.1F);
        this.LeftLowerTentacle2.addBox(-0.5F, 0.0F, 0.0F, 1, 10, 1, 0.0F);
        this.setRotateAngle(LeftLowerTentacle2, 0.4553564018453205F, 0.0F, -0.4553564018453205F);
        this.RightUpperArm = new ModelRenderer(this, 104, 21);
        this.RightUpperArm.setRotationPoint(-3.7F, 1.7F, 2.3F);
        this.RightUpperArm.addBox(-1.5F, 0.0F, 0.0F, 3, 15, 3, 0.0F);
        this.setRotateAngle(RightUpperArm, 0.27314402793711257F, 0.0F, 0.5462880558742251F);
        this.Eye2 = new ModelRenderer(this, 122, 59);
        this.Eye2.setRotationPoint(-5.0F, 6.4F, 5.0F);
        this.Eye2.addBox(-0.5F, 0.0F, 0.0F, 1, 2, 2, 0.0F);
        this.RightUpperTentacle2 = new ModelRenderer(this, 0, 0);
        this.RightUpperTentacle2.setRotationPoint(0.0F, 17.3F, 1.7F);
        this.RightUpperTentacle2.addBox(-0.5F, 0.0F, 0.0F, 1, 10, 1, 0.0F);
        this.setRotateAngle(RightUpperTentacle2, 1.7756979809790308F, -0.6373942428283291F, 0.0F);
        this.LowerBody = new ModelRenderer(this, 96, 0);
        this.LowerBody.setRotationPoint(0.0F, -7.0F, -1.0F);
        this.LowerBody.addBox(-4.5F, 0.0F, 0.0F, 9, 14, 7, 0.0F);
        this.Eye12 = new ModelRenderer(this, 117, 61);
        this.Eye12.setRotationPoint(-5.0F, 6.6F, 2.0F);
        this.Eye12.addBox(-0.5F, 0.0F, 0.0F, 1, 1, 1, 0.0F);
        this.LeftLowerTentacle1 = new ModelRenderer(this, 0, 0);
        this.LeftLowerTentacle1.setRotationPoint(0.2F, 9.6F, 0.0F);
        this.LeftLowerTentacle1.addBox(-0.5F, 0.0F, 0.0F, 1, 10, 1, 0.0F);
        this.setRotateAngle(LeftLowerTentacle1, 0.8651597102135892F, -0.22759093446006054F, 0.0F);
        this.Eye8 = new ModelRenderer(this, 122, 61);
        this.Eye8.setRotationPoint(-2.0F, 1.0F, -0.9F);
        this.Eye8.addBox(-1.0F, 0.0F, 0.0F, 2, 2, 1, 0.0F);
        this.LowerBody.addChild(this.UpperBody);
        this.LeftLowerArm.addChild(this.LeftUpperTentacle1);
        this.MainHead.addChild(this.Eye7);
        this.MainHead.addChild(this.Eye11);
        this.LeftBackUpperLeg.addChild(this.LeftBackLowerLeg);
        this.BackHead2.addChild(this.BackHead3);
        this.RightBackUpperLeg.addChild(this.RightBackLowerLeg);
        this.MainHead.addChild(this.BackHead1);
        this.UpperBody.addChild(this.Neck);
        this.LeftMidUpperLeg.addChild(this.LeftMidLowerLeg);
        this.LeftUpperTentacle3.addChild(this.LeftLowerTentacle3);
        this.MainHead.addChild(this.Eye1);
        this.RightLowerArm.addChild(this.RightUpperTentacle3);
        this.FrontHead2.addChild(this.Eye18);
        this.RightUpperTentacle1.addChild(this.RightLowerTentacle1);
        this.MainHead.addChild(this.Eye14);
        this.RightUpperTentacle2.addChild(this.RightLowerTentacle2);
        this.Neck.addChild(this.MainHead);
        this.MainHead.addChild(this.Eye5);
        this.MainHead.addChild(this.Eye17);
        this.MainHead.addChild(this.Eye3);
        this.FrontHead1.addChild(this.FrontHead2);
        this.BackHead1.addChild(this.BackHead2);
        this.LeftLowerArm.addChild(this.LeftUpperTentacle2);
        this.RightUpperTentacle3.addChild(this.RightLowerTentacle3);
        this.LeftUpperArm.addChild(this.LeftLowerArm);
        this.FrontHead2.addChild(this.Eye13);
        this.LowerBody2.addChild(this.LowerBody3);
        this.RightFrontUpperLeg.addChild(this.RightFrontLowerLeg);
        this.LowerBody1.addChild(this.LeftFrontUpperLeg);
        this.UpperBody.addChild(this.LeftUpperArm);
        this.MainHead.addChild(this.FrontHead1);
        this.LowerBody1.addChild(this.LowerBody2);
        this.RightLowerArm.addChild(this.RightUpperTentacle1);
        this.LowerBody1.addChild(this.RightBackUpperLeg);
        this.LowerBody1.addChild(this.RightFrontUpperLeg);
        this.MainHead.addChild(this.Eye16);
        this.MainHead.addChild(this.Eye15);
        this.LeftFrontUpperLeg.addChild(this.LeftFrontLowerLeg);
        this.LowerBody1.addChild(this.RightMidUpperLeg);
        this.RightUpperArm.addChild(this.RightLowerArm);
        this.MainHead.addChild(this.Eye10);
        this.LowerBody1.addChild(this.LeftMidUpperLeg);
        this.LeftLowerArm.addChild(this.LeftUpperTentacle3);
        this.RightMidUpperLeg.addChild(this.RightMidLowerLeg);
        this.MainHead.addChild(this.Eye4);
        this.MainHead.addChild(this.Eye6);
        this.LowerBody1.addChild(this.LeftBackUpperLeg);
        this.FrontHead2.addChild(this.Eye9);
        this.LeftUpperTentacle2.addChild(this.LeftLowerTentacle2);
        this.UpperBody.addChild(this.RightUpperArm);
        this.MainHead.addChild(this.Eye2);
        this.RightLowerArm.addChild(this.RightUpperTentacle2);
        this.LowerBody1.addChild(this.LowerBody);
        this.MainHead.addChild(this.Eye12);
        this.LeftUpperTentacle1.addChild(this.LeftLowerTentacle1);
        this.FrontHead2.addChild(this.Eye8);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        this.LowerBody1.render(f5);
    }

    /**
     * This is a helper function from Tabula to set the rotation of model parts
     */
    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
