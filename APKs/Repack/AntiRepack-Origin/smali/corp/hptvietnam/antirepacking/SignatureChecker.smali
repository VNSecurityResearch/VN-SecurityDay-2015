.class public Lcorp/hptvietnam/antirepacking/SignatureChecker;
.super Ljava/lang/Object;
.source "SignatureChecker.java"


# instance fields
.field private expectedSig:Ljava/lang/String;

.field private mContext:Landroid/content/Context;


# direct methods
.method constructor <init>(Landroid/content/Context;)V
    .locals 1
    .param p1, "context"    # Landroid/content/Context;

    .prologue
    .line 23
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 19
    const-string v0, "f0a5eed31e17f36a5031bbc7b77c8215"

    iput-object v0, p0, Lcorp/hptvietnam/antirepacking/SignatureChecker;->expectedSig:Ljava/lang/String;

    .line 24
    iput-object p1, p0, Lcorp/hptvietnam/antirepacking/SignatureChecker;->mContext:Landroid/content/Context;

    .line 25
    return-void
.end method

.method private getMD5(Ljava/lang/String;)Ljava/lang/String;
    .locals 5
    .param p1, "input"    # Ljava/lang/String;

    .prologue
    .line 59
    const/4 v1, 0x0

    .line 61
    .local v1, "m":Ljava/security/MessageDigest;
    :try_start_0
    const-string v2, "MD5"

    invoke-static {v2}, Ljava/security/MessageDigest;->getInstance(Ljava/lang/String;)Ljava/security/MessageDigest;

    move-result-object v1

    .line 62
    invoke-virtual {p1}, Ljava/lang/String;->getBytes()[B

    move-result-object v2

    const/4 v3, 0x0

    invoke-virtual {p1}, Ljava/lang/String;->length()I

    move-result v4

    invoke-virtual {v1, v2, v3, v4}, Ljava/security/MessageDigest;->update([BII)V

    .line 63
    new-instance v2, Ljava/math/BigInteger;

    const/4 v3, 0x1

    invoke-virtual {v1}, Ljava/security/MessageDigest;->digest()[B

    move-result-object v4

    invoke-direct {v2, v3, v4}, Ljava/math/BigInteger;-><init>(I[B)V

    const/16 v3, 0x10

    invoke-virtual {v2, v3}, Ljava/math/BigInteger;->toString(I)Ljava/lang/String;
    :try_end_0
    .catch Ljava/security/NoSuchAlgorithmException; {:try_start_0 .. :try_end_0} :catch_0

    move-result-object v2

    .line 67
    :goto_0
    return-object v2

    .line 64
    :catch_0
    move-exception v0

    .line 65
    .local v0, "e":Ljava/security/NoSuchAlgorithmException;
    invoke-virtual {v0}, Ljava/security/NoSuchAlgorithmException;->printStackTrace()V

    .line 67
    const-string v2, ""

    goto :goto_0
.end method


# virtual methods
.method public getExpectedSig()Ljava/lang/String;
    .locals 1

    .prologue
    .line 71
    iget-object v0, p0, Lcorp/hptvietnam/antirepacking/SignatureChecker;->expectedSig:Ljava/lang/String;

    return-object v0
.end method

.method public getSignature()Ljava/lang/String;
    .locals 11

    .prologue
    .line 31
    :try_start_0
    iget-object v8, p0, Lcorp/hptvietnam/antirepacking/SignatureChecker;->mContext:Landroid/content/Context;

    invoke-virtual {v8}, Landroid/content/Context;->getPackageManager()Landroid/content/pm/PackageManager;

    move-result-object v8

    iget-object v9, p0, Lcorp/hptvietnam/antirepacking/SignatureChecker;->mContext:Landroid/content/Context;

    invoke-virtual {v9}, Landroid/content/Context;->getPackageName()Ljava/lang/String;

    move-result-object v9

    const/16 v10, 0x40

    invoke-virtual {v8, v9, v10}, Landroid/content/pm/PackageManager;->getPackageInfo(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;

    move-result-object v5

    .line 32
    .local v5, "pkInfo":Landroid/content/pm/PackageInfo;
    iget-object v0, v5, Landroid/content/pm/PackageInfo;->signatures:[Landroid/content/pm/Signature;

    .local v0, "arr$":[Landroid/content/pm/Signature;
    array-length v3, v0

    .local v3, "len$":I
    const/4 v2, 0x0

    .local v2, "i$":I
    :goto_0
    if-ge v2, v3, :cond_0

    aget-object v7, v0, v2
    :try_end_0
    .catch Landroid/content/pm/PackageManager$NameNotFoundException; {:try_start_0 .. :try_end_0} :catch_1

    .line 35
    .local v7, "signature":Landroid/content/pm/Signature;
    :try_start_1
    const-string v8, "SHA"

    invoke-static {v8}, Ljava/security/MessageDigest;->getInstance(Ljava/lang/String;)Ljava/security/MessageDigest;

    move-result-object v4

    .line 36
    .local v4, "md":Ljava/security/MessageDigest;
    invoke-virtual {v7}, Landroid/content/pm/Signature;->toByteArray()[B

    move-result-object v8

    invoke-virtual {v4, v8}, Ljava/security/MessageDigest;->update([B)V

    .line 37
    invoke-virtual {v4}, Ljava/security/MessageDigest;->digest()[B

    move-result-object v8

    const/4 v9, 0x0

    invoke-static {v8, v9}, Landroid/util/Base64;->encodeToString([BI)Ljava/lang/String;

    move-result-object v6

    .line 39
    .local v6, "result":Ljava/lang/String;
    invoke-direct {p0, v6}, Lcorp/hptvietnam/antirepacking/SignatureChecker;->getMD5(Ljava/lang/String;)Ljava/lang/String;
    :try_end_1
    .catch Ljava/security/NoSuchAlgorithmException; {:try_start_1 .. :try_end_1} :catch_0
    .catch Landroid/content/pm/PackageManager$NameNotFoundException; {:try_start_1 .. :try_end_1} :catch_1

    move-result-object v8

    .line 48
    .end local v0    # "arr$":[Landroid/content/pm/Signature;
    .end local v2    # "i$":I
    .end local v3    # "len$":I
    .end local v4    # "md":Ljava/security/MessageDigest;
    .end local v5    # "pkInfo":Landroid/content/pm/PackageInfo;
    .end local v6    # "result":Ljava/lang/String;
    .end local v7    # "signature":Landroid/content/pm/Signature;
    :goto_1
    return-object v8

    .line 40
    .restart local v0    # "arr$":[Landroid/content/pm/Signature;
    .restart local v2    # "i$":I
    .restart local v3    # "len$":I
    .restart local v5    # "pkInfo":Landroid/content/pm/PackageInfo;
    .restart local v7    # "signature":Landroid/content/pm/Signature;
    :catch_0
    move-exception v1

    .line 41
    .local v1, "e":Ljava/security/NoSuchAlgorithmException;
    :try_start_2
    invoke-virtual {v1}, Ljava/security/NoSuchAlgorithmException;->printStackTrace()V
    :try_end_2
    .catch Landroid/content/pm/PackageManager$NameNotFoundException; {:try_start_2 .. :try_end_2} :catch_1

    .line 32
    add-int/lit8 v2, v2, 0x1

    goto :goto_0

    .line 44
    .end local v0    # "arr$":[Landroid/content/pm/Signature;
    .end local v1    # "e":Ljava/security/NoSuchAlgorithmException;
    .end local v2    # "i$":I
    .end local v3    # "len$":I
    .end local v5    # "pkInfo":Landroid/content/pm/PackageInfo;
    .end local v7    # "signature":Landroid/content/pm/Signature;
    :catch_1
    move-exception v1

    .line 45
    .local v1, "e":Landroid/content/pm/PackageManager$NameNotFoundException;
    invoke-virtual {v1}, Landroid/content/pm/PackageManager$NameNotFoundException;->printStackTrace()V

    .line 48
    .end local v1    # "e":Landroid/content/pm/PackageManager$NameNotFoundException;
    :cond_0
    const-string v8, ""

    goto :goto_1
.end method

.method public isModified()Z
    .locals 3

    .prologue
    .line 52
    invoke-virtual {p0}, Lcorp/hptvietnam/antirepacking/SignatureChecker;->getSignature()Ljava/lang/String;

    move-result-object v1

    .line 53
    .local v1, "sig":Ljava/lang/String;
    invoke-virtual {p0}, Lcorp/hptvietnam/antirepacking/SignatureChecker;->getExpectedSig()Ljava/lang/String;

    move-result-object v0

    .line 54
    .local v0, "expectedSig":Ljava/lang/String;
    const-string v2, "App-Sig"

    invoke-static {v2, v1}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 55
    invoke-virtual {v1, v0}, Ljava/lang/String;->equalsIgnoreCase(Ljava/lang/String;)Z

    move-result v2

    if-nez v2, :cond_0

    const/4 v2, 0x1

    :goto_0
    return v2

    :cond_0
    const/4 v2, 0x0

    goto :goto_0
.end method
