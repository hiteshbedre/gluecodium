/*
 * Copyright (C) 2017 HERE Global B.V. and its affiliate(s). All rights reserved.
 *
 * This software, including documentation, is protected by copyright controlled by
 * HERE Global B.V. All rights are reserved. Copying, including reproducing, storing,
 * adapting or translating, any or all of this material requires the prior written
 * consent of HERE Global B.V. This material also contains confidential information,
 * which may not be disclosed to others without prior written consent of HERE Global B.V.
 *
 */

package com.here.ivi.api.generator.android;

import com.here.ivi.api.generator.common.GeneratedFile;
import com.here.ivi.api.generator.common.android.AndroidNameRules;
import com.here.ivi.api.generator.common.android.templates.AndroidManifestTemplate;
import java.util.ArrayList;
import java.util.List;

final class AndroidManifestGenerator extends AbstractAndroidGenerator {

  public AndroidManifestGenerator(final List<String> packageList) {
    super(packageList);
  }

  List<GeneratedFile> generate() {

    CharSequence fileContent = AndroidManifestTemplate.generate(basePackage);

    List<GeneratedFile> files = new ArrayList<>();
    files.add(new GeneratedFile(fileContent, AndroidNameRules.getManifestFilename()));
    return files;
  }
}
