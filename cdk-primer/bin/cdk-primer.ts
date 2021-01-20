#!/usr/bin/env node
import 'source-map-support/register';
import * as cdk from '@aws-cdk/core';
import { CdkPrimerStack } from '../lib/cdk-primer-stack';

const app = new cdk.App();
new CdkPrimerStack(app, 'CdkPrimerStack');
